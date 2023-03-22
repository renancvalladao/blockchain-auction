import { CircularProgress, Text, VStack } from '@chakra-ui/react'

const AuctionProgress = () => {
  return (
    <VStack>
      <Text fontWeight={'medium'}>Auction in progress...</Text>
      <CircularProgress isIndeterminate size={'120px'} />
    </VStack>
  )
}

export default AuctionProgress
