package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuctionService {

    private static final int BIDDING_TIME = 10;
    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void createAuction(RequirementsRequest requirementsRequest) throws Exception {
        Auction.Requirements requirements = this.requirementsRequestToAuctionRequirements(requirementsRequest);
        Auction auction = Auction.deploy(this.web3j, this.transactionManager, new DefaultGasProvider(), requirements,
                BigInteger.valueOf(BIDDING_TIME)).send();
        this.executorService.schedule(() -> {
            try {
                auction.finishAuction().send();
                log.info("Auction finished successfully");
            } catch (Exception e) {
                log.error("Failed to finish auction", e);
            }
        }, BIDDING_TIME, TimeUnit.SECONDS);
        // TODO: call each vnf provider bid endpoint
    }

    private Auction.Requirements requirementsRequestToAuctionRequirements(RequirementsRequest requirementsRequest) {
        return new Auction.Requirements(requirementsRequest.getVnfName(), requirementsRequest.getVnfType(),
                BigInteger.valueOf(requirementsRequest.getNumCpus()));
    }

}
