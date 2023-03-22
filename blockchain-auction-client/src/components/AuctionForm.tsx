import {
  Button,
  FormControl,
  FormLabel,
  Input,
  NumberDecrementStepper,
  NumberIncrementStepper,
  NumberInput,
  NumberInputField,
  NumberInputStepper,
  Stack
} from '@chakra-ui/react'
import { FormEvent, useState } from 'react'

type AuctionFormProps = {
  isLoading: boolean
  onCreateAuction: (requirementsRequest: RequirementsRequest) => void
}

const AuctionForm = ({ onCreateAuction, isLoading }: AuctionFormProps) => {
  const [vnfName, setVnfName] = useState('')
  const [vnfType, setVnfType] = useState('')
  const [numCpus, setNumCpus] = useState(0)

  const submitHandler = (e: FormEvent) => {
    e.preventDefault()
    onCreateAuction({ vnfName, vnfType, numCpus })
  }

  return (
    <form onSubmit={submitHandler}>
      <Stack spacing={4}>
        <FormControl>
          <FormLabel>Identifier</FormLabel>
          <Input value={vnfName} onChange={(e) => setVnfName(e.target.value)} />
        </FormControl>
        <FormControl>
          <FormLabel>Type</FormLabel>
          <Input value={vnfType} onChange={(e) => setVnfType(e.target.value)} />
        </FormControl>
        <FormControl>
          <FormLabel>Number of CPUs</FormLabel>
          <NumberInput
            value={numCpus}
            max={20}
            min={0}
            onChange={(value) => setNumCpus(+value)}
          >
            <NumberInputField />
            <NumberInputStepper>
              <NumberIncrementStepper />
              <NumberDecrementStepper />
            </NumberInputStepper>
          </NumberInput>
        </FormControl>
        <Button
          type="submit"
          colorScheme={'blue'}
          isLoading={isLoading}
          loadingText={'Submitting'}
        >
          Submit
        </Button>
      </Stack>
    </form>
  )
}

export default AuctionForm
