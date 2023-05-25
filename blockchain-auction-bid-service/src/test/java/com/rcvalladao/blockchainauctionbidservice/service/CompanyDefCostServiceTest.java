package com.rcvalladao.blockchainauctionbidservice.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyDefCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyDefCostService companyDefCostService = new CompanyDefCostService();
        int numCpus = 4;
        int memSize = 3;
        int expectedCost = numCpus * 12 + memSize * 4 + 4 + 2;
        Auction.Requirements requirements = new Auction.Requirements("name", "type", BigInteger.valueOf(numCpus),
                BigInteger.valueOf(memSize), new Auction.OptionalRequirement(BigInteger.ONE, true),
                new Auction.OptionalRequirement(BigInteger.ONE, false));
        int actualCost = companyDefCostService.calculateCost(requirements);
        assertEquals(expectedCost, actualCost);
    }

}