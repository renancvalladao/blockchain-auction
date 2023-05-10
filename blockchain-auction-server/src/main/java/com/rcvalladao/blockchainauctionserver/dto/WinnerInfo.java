package com.rcvalladao.blockchainauctionserver.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class WinnerInfo {

    private final String address;
    private final String name;
    private final int cost;

}
