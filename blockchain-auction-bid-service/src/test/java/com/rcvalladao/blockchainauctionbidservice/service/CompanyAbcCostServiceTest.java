package com.rcvalladao.blockchainauctionbidservice.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyAbcCostServiceTest {

    @Test
    void givenSpec_whenCalculateCost_ThenReturnCost() {
        CompanyAbcCostService companyAbcCostService = new CompanyAbcCostService();
        int numCpus = 4;
        int memSize = 2;
        int expectedCost = numCpus * 10 + memSize * 5 + 4 + 2;
        Auction.Requirements requirements = new Auction.Requirements("name", "type", BigInteger.valueOf(numCpus),
                BigInteger.valueOf(memSize), new Auction.OptionalRequirement(BigInteger.ONE, true),
                new Auction.OptionalRequirement(BigInteger.ONE, false));
        int actualCost = companyAbcCostService.calculateCost(requirements);
        assertEquals(expectedCost, actualCost);
    }

}