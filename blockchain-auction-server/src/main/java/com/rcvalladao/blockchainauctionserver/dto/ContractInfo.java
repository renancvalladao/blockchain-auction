package com.rcvalladao.blockchainauctionserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractInfo {

    private String address;
    private String ownerAddress;

}
