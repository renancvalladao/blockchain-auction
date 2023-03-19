package com.rcvalladao.blockchainauctionbidservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyAbcCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyAbcCostService companyAbcCostService = new CompanyAbcCostService();
        int numCpus = 4;
        int expectedCost = numCpus * 10;
        int actualCost = companyAbcCostService.calculateCost(numCpus);
        assertEquals(expectedCost, actualCost);
    }

}