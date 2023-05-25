import {
  Button,
  Checkbox,
  Flex,
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
  const [memSize, setMemSize] = useState(0)
  const [maxDelayValue, setMaxDelayValue] = useState(0)
  const [maxDelayRequired, setMaxDelayRequired] = useState(false)
  const [bandwidthValue, setBandwidthValue] = useState(0)
  const [bandwidthRequired, setBandwidthRequired] = useState(false)

  const submitHandler = (e: FormEvent) => {
    e.preventDefault()
    const maxDelay: OptionalRequirement = {
      value: maxDelayValue,
      required: maxDelayRequired
    }
    const bandwidth: OptionalRequirement = {
      value: bandwidthValue,
      required: bandwidthRequired
    }
    onCreateAuction({
      vnfName,
      vnfType,
      numCpus,
      memSize,
      maxDelay,
      bandwidth
    })
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
        <FormControl>
          <FormLabel>Memory Size (GB)</FormLabel>
          <NumberInput
            value={memSize}
            max={64}
            min={0}
            onChange={(value) => setMemSize(+value)}
          >
            <NumberInputField />
            <NumberInputStepper>
              <NumberIncrementStepper />
              <NumberDecrementStepper />
            </NumberInputStepper>
          </NumberInput>
        </FormControl>
        <FormControl>
          <FormLabel>Max Delay (s)</FormLabel>
          <Flex>
            <NumberInput
              flexGrow={1}
              value={maxDelayValue}
              onChange={(value) => setMaxDelayValue(+value)}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
            <Checkbox
              ml={4}
              isChecked={maxDelayRequired}
              onChange={(e) => setMaxDelayRequired(e.target.checked)}
            >
              Required
            </Checkbox>
          </Flex>
        </FormControl>
        <FormControl>
          <FormLabel>Bandwidth</FormLabel>
          <Flex>
            <NumberInput
              flexGrow={1}
              value={bandwidthValue}
              onChange={(value) => setBandwidthValue(+value)}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
            <Checkbox
              ml={4}
              isChecked={bandwidthRequired}
              onChange={(e) => setBandwidthRequired(e.target.checked)}
            >
              Required
            </Checkbox>
          </Flex>
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
