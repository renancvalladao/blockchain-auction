import {
  Box,
  Button,
  Stack,
  Table,
  TableContainer,
  Tbody,
  Td,
  Text,
  Th,
  Thead,
  Tr
} from '@chakra-ui/react'

type AuctionResultProps = {
  winner: WinnerInfo
  onNewAuction: () => void
  bidHistory: Bid[]
}

const AuctionResult = ({
  winner,
  onNewAuction,
  bidHistory
}: AuctionResultProps) => {
  return (
    <Stack spacing={4}>
      <Box>
        <Text textAlign={'center'} fontWeight={'medium'}>
          Winner: {winner.name}
        </Text>
        <Text textAlign={'center'} fontWeight={'medium'}>
          Address: {winner.address}
        </Text>
        <Text textAlign={'center'} fontWeight={'medium'}>
          Cost: {winner.cost}
        </Text>
      </Box>
      <TableContainer>
        <Table variant={'striped'}>
          <Thead>
            <Tr>
              <Th>Bidder</Th>
              <Th>Value</Th>
            </Tr>
          </Thead>
          <Tbody>
            {bidHistory.map((bid) => (
              <Tr key={bid.bidder}>
                <Td>{bid.bidder}</Td>
                <Td>{bid.value}</Td>
              </Tr>
            ))}
          </Tbody>
        </Table>
      </TableContainer>
      <Button colorScheme={'blue'} onClick={onNewAuction}>
        New auction
      </Button>
    </Stack>
  )
}

export default AuctionResult
