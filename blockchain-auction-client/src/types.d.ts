type RequirementsRequest = {
  vnfName: string
  vnfType: string
  numCpus: number
  memSize: number
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
