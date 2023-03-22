import { Button, Stack, Text } from '@chakra-ui/react'

type AuctionResultProps = {
  winner: string
  onNewAuction: () => void
}

const AuctionResult = ({ winner, onNewAuction }: AuctionResultProps) => {
  return (
    <Stack spacing={4}>
      <Text textAlign={'center'} fontWeight={'medium'}>
        Winner: {winner}
      </Text>
      <Button colorScheme={'blue'} onClick={onNewAuction}>
        New auction
      </Button>
    </Stack>
  )
}

export default AuctionResult
