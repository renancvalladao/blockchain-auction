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
public class CompanyDefBidService {

    private final Web3j web3j;
    private final TransactionManager companyDefTransactionManager;
    private final CompanyDefCostService companyDefCostService;

    public void placeBid(ContractInfo contractInfo) throws Exception {
        Auction auction = Auction.load(contractInfo.getAddress(), this.web3j, this.companyDefTransactionManager, new DefaultGasProvider());
        Auction.Requirements requirements = auction.getRequirements().send();
        int cost = this.companyDefCostService.calculateCost(requirements);
        auction.placeBid(BigInteger.valueOf(cost)).sendAsync().thenAccept(action -> log.info("Bid placed " + contractInfo.getAddress()));
    }

}
