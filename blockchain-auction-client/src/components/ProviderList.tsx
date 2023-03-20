import {
  Table,
  TableContainer,
  Tbody,
  Td,
  Th,
  Thead,
  Tr
} from '@chakra-ui/react'

const ProviderList = () => {
  return (
    <TableContainer>
      <Table variant={'striped'}>
        <Thead>
          <Tr>
            <Th>Name</Th>
            <Th>Endpoint</Th>
          </Tr>
        </Thead>
        <Tbody>
          <Tr>
            <Td>Company ABC</Td>
            <Td>http://localhost:8081/company-abc/bids</Td>
          </Tr>
          <Tr>
            <Td>Company DEF</Td>
            <Td>http://localhost:8081/company-def/bids</Td>
          </Tr>
        </Tbody>
      </Table>
    </TableContainer>
  )
}

export default ProviderList
