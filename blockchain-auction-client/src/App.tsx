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
import ProviderForm from './components/ProviderForm'
import ProvidersList from './components/ProvidersList'

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
