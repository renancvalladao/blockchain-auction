package com.rcvalladao.blockchainauctionbenchmark.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuctionResult {

    private final long deployDuration;
    private final long auctionDuration;

}
