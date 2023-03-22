type RequirementsRequest = {
  vnfName: string
  vnfType: string
  numCpus: number
}

type ProviderInfo = {
  name: string
  bidEndpoint: string
}

type ContractInfo = {
  address: string
  ownerAddress: string
}
