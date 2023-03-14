package com.rcvalladao.blockchainauctionserver.contract;

import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EVMTest
class AuctionTest {

    @Test
    void givenDescriptor_whenDeploy_thenReturnDescriptor(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        Auction.Requirements expectedRequirements = new Auction.Requirements("Name", "Type", BigInteger.TWO);
        Auction auction = Auction.deploy(web3j, transactionManager, gasProvider, expectedRequirements).send();
        Auction.Requirements actualRequirements = auction.getRequirements().send();
        assertEquals(expectedRequirements.vnfName, actualRequirements.vnfName);
        assertEquals(expectedRequirements.vnfType, actualRequirements.vnfType);
        assertEquals(expectedRequirements.numCpus, actualRequirements.numCpus);
    }

}