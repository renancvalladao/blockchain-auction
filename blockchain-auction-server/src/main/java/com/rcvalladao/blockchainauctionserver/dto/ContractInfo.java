package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class ContractInfo {

    private final String address;
    private final String ownerAddress;

}
