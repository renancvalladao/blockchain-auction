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

const AuctionForm = () => {
  return (
    <form>
      <Stack spacing={4}>
        <FormControl>
          <FormLabel>Identifier</FormLabel>
          <Input />
        </FormControl>
        <FormControl>
          <FormLabel>Type</FormLabel>
          <Input />
        </FormControl>
        <FormControl>
          <FormLabel>Number of CPUs</FormLabel>
          <NumberInput defaultValue={0} max={20} min={0}>
            <NumberInputField />
            <NumberInputStepper>
              <NumberIncrementStepper />
              <NumberDecrementStepper />
            </NumberInputStepper>
          </NumberInput>
        </FormControl>
        <Button type="submit" colorScheme={'blue'}>
          Submit
        </Button>
      </Stack>
    </form>
  )
}

export default AuctionForm
