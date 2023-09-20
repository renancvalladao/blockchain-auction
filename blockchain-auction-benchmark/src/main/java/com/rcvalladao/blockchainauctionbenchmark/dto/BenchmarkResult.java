package com.rcvalladao.blockchainauctionbenchmark.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BenchmarkResult {

    private final long numberOfAuctions;
    private final long numberOfProviders;
    private final List<AuctionResult> auctionResults;
    private final long averageDeployDuration;
    private final long averageAuctionDuration;

}
