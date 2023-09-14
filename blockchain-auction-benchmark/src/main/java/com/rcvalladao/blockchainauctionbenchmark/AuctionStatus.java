package com.rcvalladao.blockchainauctionbenchmark;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class AuctionStatus {
    private boolean finished;
    private Instant startedAt;
    private Instant deployedAt;
    private Instant finishedAt;
}
