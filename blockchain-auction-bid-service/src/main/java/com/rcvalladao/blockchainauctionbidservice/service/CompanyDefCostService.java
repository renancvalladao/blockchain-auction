package com.rcvalladao.blockchainauctionbidservice.service;

import org.springframework.stereotype.Service;

@Service
public class CompanyDefCostService {

    private static final int COST_PER_CPU = 12;

    public int calculateCost(int numCpus) {
        return COST_PER_CPU * numCpus;
    }

}
