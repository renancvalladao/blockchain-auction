package com.rcvalladao.blockchainauctionbidservice.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@RequiredArgsConstructor
@Slf4j
@Service
public class CompanyAbcBidService {

    private final Web3j web3j;
    private final TransactionManager companyAbcTransactionManager;

    public void placeBid(ContractInfo contractInfo) {
        Auction auction = Auction.load(contractInfo.getAddress(), this.web3j, this.companyAbcTransactionManager, new DefaultGasProvider());
        long cost = 100; // TODO: calculate service cost dynamically
        auction.placeBid(BigInteger.valueOf(cost));
    }

}
