package com.rcvalladao.blockchainauctionserver.contract;

import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@EVMTest
class AuctionTest {

    @Test
    void givenRequirements_whenGetRequirements_thenReturnRequirements(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        Auction.Requirements expectedRequirements = new Auction.Requirements("Name", "Type", BigInteger.TWO);
        Auction auction = Auction.deploy(web3j, transactionManager, gasProvider, expectedRequirements, BigInteger.ONE).send();
        Auction.Requirements actualRequirements = auction.getRequirements().send();

        assertEquals(expectedRequirements.vnfName, actualRequirements.vnfName);
        assertEquals(expectedRequirements.vnfType, actualRequirements.vnfType);
        assertEquals(expectedRequirements.numCpus, actualRequirements.numCpus);
    }

    @Test
    void givenAuctionEnded_whenFinishAuction_thenReturnSuccess(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        Auction.Requirements requirements = new Auction.Requirements("Name", "Type", BigInteger.TWO);
        Auction auction = Auction.deploy(web3j, transactionManager, gasProvider, requirements, BigInteger.ZERO).send();
        auction.finishAuction().send();
    }

    @Test
    void givenAuctionNotEnded_whenFinishAuction_thenReturnError(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        Auction.Requirements requirements = new Auction.Requirements("Name", "Type", BigInteger.TWO);
        Auction auction = Auction.deploy(web3j, transactionManager, gasProvider, requirements, BigInteger.valueOf(5000)).send();

        assertThrows(TransactionException.class, () -> auction.finishAuction().send());
    }

    @Test
    void givenNotOwner_whenFinishAuction_thenReturnError(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        Auction.Requirements requirements = new Auction.Requirements("Name", "Type", BigInteger.TWO);
        Auction auction = Auction.deploy(web3j, transactionManager, gasProvider, requirements, BigInteger.ZERO).send();

        // Using another account
        Credentials notOwner = Credentials.create("a");
        transactionManager.sendTransaction(gasProvider.getGasPrice(""), gasProvider.getGasLimit(""),
                notOwner.getAddress(), "", Convert.toWei("1.0", Convert.Unit.ETHER).toBigInteger());
        TransactionManager myTransactionManager = new RawTransactionManager(web3j, notOwner);
        Auction notOwnerAuction = Auction.load(auction.getContractAddress(), web3j, myTransactionManager, gasProvider);

        assertThrows(TransactionException.class, () -> notOwnerAuction.finishAuction().send());
    }

}