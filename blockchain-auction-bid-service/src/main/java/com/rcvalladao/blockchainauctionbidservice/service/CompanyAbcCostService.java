package com.rcvalladao.blockchainauctionbidservice.service;

import org.springframework.stereotype.Service;

@Service
public class CompanyAbcCostService {

    private static final int COST_PER_CPU = 10;

    public int calculateCost(int numCpus) {
        return COST_PER_CPU * numCpus;
    }

}
