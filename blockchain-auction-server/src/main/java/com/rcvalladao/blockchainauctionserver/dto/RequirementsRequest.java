package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequirementsRequest {

    private final String vnfName;
    private final String vnfType;
    private final int numCpus;

}
