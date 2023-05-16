package com.rcvalladao.blockchainauctionbidservice.service;

import org.springframework.stereotype.Service;

@Service
public class CompanyDefCostService {

    private static final int COST_PER_CPU = 12;
    private static final int COST_PER_MEM = 4;

    public int calculateCost(int numCpus, int memSize) {
        int cpuCost = COST_PER_CPU * numCpus;
        int memCost = COST_PER_MEM * memSize;
        return cpuCost + memCost;
    }

}
