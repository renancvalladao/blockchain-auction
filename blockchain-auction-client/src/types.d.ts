type OptionalRequirement = {
  value: number
  required: boolean
}

type RequirementsRequest = {
  vnfName: string
  vnfType: string
  numCpus: number
  memSize: number
  maxDelay: OptionalRequirement
  bandwidth: OptionalRequirement
}

type ProviderInfo = {
  name: string
  address: string
}

type ContractInfo = {
  address: string
  ownerAddress: string
}

type WinnerInfo = {
  address: string
  name: string
  cost: number
}

type Bid = {
  bidder: string
  value: int
}
