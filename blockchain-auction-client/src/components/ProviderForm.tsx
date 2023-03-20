import { Button, FormControl, FormLabel, Input, Stack } from '@chakra-ui/react'
import { FormEvent, useState } from 'react'

type ProviderFormProps = {
  onAddProvider: (providerInfo: ProviderInfo) => void
}

const ProviderForm = ({ onAddProvider }: ProviderFormProps) => {
  const [name, setName] = useState('')
  const [bidEndpoint, setBidEndpoint] = useState('')

  const submitHandler = (e: FormEvent) => {
    e.preventDefault()
    onAddProvider({ name, bidEndpoint })
    setName('')
    setBidEndpoint('')
  }

  return (
    <form onSubmit={submitHandler}>
      <Stack spacing={4}>
        <FormControl>
          <FormLabel>Name</FormLabel>
          <Input value={name} onChange={(e) => setName(e.target.value)} />
        </FormControl>
        <FormControl>
          <FormLabel>Endpoint</FormLabel>
          <Input
            value={bidEndpoint}
            onChange={(e) => setBidEndpoint(e.target.value)}
          />
        </FormControl>
        <Button type="submit" colorScheme={'blue'}>
          Add new provider
        </Button>
      </Stack>
    </form>
  )
}

export default ProviderForm
