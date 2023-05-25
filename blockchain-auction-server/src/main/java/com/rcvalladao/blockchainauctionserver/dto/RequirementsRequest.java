package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class RequirementsRequest {

    private final String vnfName;
    private final String vnfType;
    private final int numCpus;
    private final int memSize;
    private final OptionalRequirement maxDelay;
    private final OptionalRequirement bandwidth;

}
