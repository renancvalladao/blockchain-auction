import { Box, Button, Stack, Text } from '@chakra-ui/react'

type AuctionResultProps = {
  winner: WinnerInfo
  onNewAuction: () => void
}

const AuctionResult = ({ winner, onNewAuction }: AuctionResultProps) => {
  return (
    <Stack spacing={4}>
      <Box>
        <Text textAlign={'center'} fontWeight={'medium'}>
          Winner: {winner.address}
        </Text>
        <Text textAlign={'center'} fontWeight={'medium'}>
          Cost: {winner.cost}
        </Text>
      </Box>
      <Button colorScheme={'blue'} onClick={onNewAuction}>
        New auction
      </Button>
    </Stack>
  )
}

export default AuctionResult
