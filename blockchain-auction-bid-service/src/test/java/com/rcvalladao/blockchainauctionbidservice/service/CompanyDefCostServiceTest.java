package com.rcvalladao.blockchainauctionbidservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyDefCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyDefCostService companyDefCostService = new CompanyDefCostService();
        int numCpus = 4;
        int memSize = 3;
        int expectedCost = numCpus * 12 + memSize * 4;
        int actualCost = companyDefCostService.calculateCost(numCpus, memSize);
        assertEquals(expectedCost, actualCost);
    }

}