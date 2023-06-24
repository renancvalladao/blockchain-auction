import {
  Card,
  CardBody,
  Heading,
  Stack,
  Tab,
  TabList,
  TabPanel,
  TabPanels,
  Tabs,
  useColorModeValue
} from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import AuctionForm from './components/AuctionForm'
import AuctionProgress from './components/AuctionProgress'
import AuctionResult from './components/AuctionResult'
import ProvidersList from './components/ProvidersList'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

enum AuctionState {
  NOT_STARTED,
  STARTED,
  FINISHED
}

const App = () => {
  const [auctionState, setAuctionState] = useState<AuctionState>(
    AuctionState.NOT_STARTED
  )
  const [auctionWinner, setAuctionWinner] = useState<WinnerInfo>()
  const [isLoading, setIsLoading] = useState(false)
  const [providers, setProviders] = useState<ProviderInfo[]>([])
  const [bidHistory, setBidHistory] = useState<Bid[]>([])
  const [stompClient, setStompClient] = useState<any>(null)

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws/client')
    const stompClient = Stomp.over(socket)
    stompClient.connect({})
    setStompClient(stompClient)

    return () => stompClient.disconnect()
  }, [])

  useEffect(() => {
    const fetchProviders = async () => {
      const response = await fetch('http://localhost:8080/providers')
      const data = await response.json()
      setProviders(data)
    }

    fetchProviders()
  }, [])

  const createAuctionHandler = async (
    requirementsRequest: RequirementsRequest
  ) => {
    setIsLoading(true)
    const response = await fetch('http://localhost:8080/auctions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requirementsRequest)
    })
    const data: ContractInfo = await response.json()
    const auctionAddress = data.address
    setIsLoading(false)
    setAuctionState(AuctionState.STARTED)
    const subscription = stompClient.subscribe(
      `/auction-finished/${auctionAddress}`,
      (payload: any) => {
        console.log(JSON.parse(payload.body))
        const result = JSON.parse(payload.body)
        const winnerInfo: WinnerInfo = result['winnerInfo']
        const bidHistory: Bid[] = result['bidHistory']
        setAuctionState(AuctionState.FINISHED)
        setAuctionWinner(winnerInfo)
        setBidHistory(bidHistory)
        subscription.unsubscribe()
      }
    )
  }

  const newAuctionHandler = () => {
    setAuctionState(AuctionState.NOT_STARTED)
  }

  return (
    <Stack spacing={16} alignItems={'center'} mt={16}>
      <Heading color={useColorModeValue('gray.700', 'gray.200')}>
        Blockchain Auction
      </Heading>
      <Card minW={'lg'}>
        <CardBody>
          <Tabs isFitted>
            <TabList>
              <Tab fontWeight={'medium'}>Auction</Tab>
              <Tab fontWeight={'medium'}>Providers</Tab>
            </TabList>
            <TabPanels>
              <TabPanel>
                {auctionState === AuctionState.NOT_STARTED && (
                  <AuctionForm
                    isLoading={isLoading}
                    onCreateAuction={createAuctionHandler}
                  />
                )}
                {auctionState === AuctionState.STARTED && <AuctionProgress />}
                {auctionState === AuctionState.FINISHED && auctionWinner && (
                  <AuctionResult
                    winner={auctionWinner}
                    onNewAuction={newAuctionHandler}
                    bidHistory={bidHistory}
                  />
                )}
              </TabPanel>
              <TabPanel>
                <ProvidersList providers={providers} />
              </TabPanel>
            </TabPanels>
          </Tabs>
        </CardBody>
      </Card>
    </Stack>
  )
}

export default App
