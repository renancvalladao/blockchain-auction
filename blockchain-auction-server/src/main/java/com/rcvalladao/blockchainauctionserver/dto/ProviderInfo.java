package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class ProviderInfo {

    private final String name;
    private final String address;

}
