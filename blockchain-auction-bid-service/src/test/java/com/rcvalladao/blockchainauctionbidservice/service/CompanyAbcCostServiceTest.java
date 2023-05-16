package com.rcvalladao.blockchainauctionbidservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyAbcCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyAbcCostService companyAbcCostService = new CompanyAbcCostService();
        int numCpus = 4;
        int memSize = 2;
        int expectedCost = numCpus * 10 + memSize * 5;
        int actualCost = companyAbcCostService.calculateCost(numCpus, memSize);
        assertEquals(expectedCost, actualCost);
    }

}