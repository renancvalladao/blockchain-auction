import {
  Table,
  TableContainer,
  Tbody,
  Td,
  Th,
  Thead,
  Tr
} from '@chakra-ui/react'

type ProvidersListProps = {
  providers: ProviderInfo[]
}

const ProvidersList = ({ providers }: ProvidersListProps) => {
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
          {providers.map((provider) => (
            <Tr key={provider.name}>
              <Td>{provider.name}</Td>
              <Td>{provider.bidEndpoint}</Td>
            </Tr>
          ))}
        </Tbody>
      </Table>
    </TableContainer>
  )
}

export default ProvidersList
