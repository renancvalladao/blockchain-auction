package com.rcvalladao.blockchainauctionbidservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyDefCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyDefCostService companyDefCostService = new CompanyDefCostService();
        int numCpus = 4;
        int expectedCost = numCpus * 12;
        int actualCost = companyDefCostService.calculateCost(numCpus);
        assertEquals(expectedCost, actualCost);
    }

}