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
import ProviderForm from './components/ProviderForm'
import ProvidersList from './components/ProvidersList'

enum AuctionState {
  NOT_STARTED,
  STARTED,
  FINISHED
}

const App = () => {
  const [auctionState, setAuctionState] = useState<AuctionState>(
    AuctionState.NOT_STARTED
  )
  const [isLoading, setIsLoading] = useState(false)
  const [providers, setProviders] = useState<ProviderInfo[]>([])

  useEffect(() => {
    const fetchProviders = async () => {
      const response = await fetch('http://localhost:8080/providers')
      const data = await response.json()
      setProviders(data)
    }

    fetchProviders()
  }, [])

  const addProviderHandler = async (providerInfo: ProviderInfo) => {
    const response = await fetch('http://localhost:8080/providers', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(providerInfo)
    })
    if (response.ok) {
      setProviders((prevProviders) => [...prevProviders, providerInfo])
    }
  }

  const createAuctionHandler = async (
    requirementsRequest: RequirementsRequest
  ) => {
    setIsLoading(true)
    await fetch('http://localhost:8080/auctions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requirementsRequest)
    })
    setIsLoading(false)
    setAuctionState(AuctionState.STARTED)
    setTimeout(() => setAuctionState(AuctionState.FINISHED), 20000)
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
                {auctionState === AuctionState.FINISHED && (
                  <AuctionResult onNewAuction={newAuctionHandler} />
                )}
              </TabPanel>
              <TabPanel>
                <Stack spacing={4}>
                  <ProvidersList providers={providers} />
                  <ProviderForm onAddProvider={addProviderHandler} />
                </Stack>
              </TabPanel>
            </TabPanels>
          </Tabs>
        </CardBody>
      </Card>
    </Stack>
  )
}

export default App
