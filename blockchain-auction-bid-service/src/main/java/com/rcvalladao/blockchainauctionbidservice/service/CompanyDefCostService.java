package com.rcvalladao.blockchainauctionbidservice.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import org.springframework.stereotype.Service;

@Service
public class CompanyDefCostService {

    private static final int COST_PER_CPU = 12;
    private static final int COST_PER_MEM = 4;
    private static final int COST_MAX_DELAY = 4;
    private static final int COST_BANDWIDTH = 2;

    public int calculateCost(Auction.Requirements requirements) {
        int maxDelayAvailable = 0;
        boolean isBandwidthAvailable = true;
        if (requirements.maxDelay.required) {
            if (requirements.maxDelay.value.intValue() < maxDelayAvailable) {
                throw new IllegalStateException("Max delay requirement not available");
            }
        }
        if (requirements.bandwidth.required) {
            if (!isBandwidthAvailable) {
                throw new IllegalStateException("Bandwidth requirement not available");
            }
        }
        int cpuCost = COST_PER_CPU * requirements.numCpus.intValue();
        int memCost = COST_PER_MEM * requirements.memSize.intValue();
        return cpuCost + memCost + COST_MAX_DELAY + COST_BANDWIDTH;
    }

}
