package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContractInfo {

    private final String address;
    private final String ownerAddress;

}
