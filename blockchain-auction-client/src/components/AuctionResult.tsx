import { Button, Stack, Text } from '@chakra-ui/react'

type AuctionResultProps = {
  onNewAuction: () => void
}

const AuctionResult = ({ onNewAuction }: AuctionResultProps) => {
  return (
    <Stack spacing={4}>
      <Text textAlign={'center'} fontWeight={'medium'}>
        Winner: Company ABC
      </Text>
      <Button colorScheme={'blue'} onClick={onNewAuction}>
        New auction
      </Button>
    </Stack>
  )
}

export default AuctionResult
