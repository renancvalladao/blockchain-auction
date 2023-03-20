import {
  Button,
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
import ProviderList from './components/ProviderList'

const App = () => {
  const [providers, setProviders] = useState<ProviderInfo[]>([])

  useEffect(() => {
    const fetchProviders = async () => {
      const response = await fetch('http://localhost:8080/providers')
      const data = await response.json()
      setProviders(data)
    }

    fetchProviders()
  }, [])

  const createAuctionHandler = (requirementsRequest: RequirementsRequest) => {
    console.log(requirementsRequest)
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
                <AuctionForm onCreateAuction={createAuctionHandler} />
              </TabPanel>
              <TabPanel>
                <Stack spacing={4}>
                  <ProviderList providers={providers} />
                  <Button colorScheme={'blue'}>Add new provider</Button>
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
