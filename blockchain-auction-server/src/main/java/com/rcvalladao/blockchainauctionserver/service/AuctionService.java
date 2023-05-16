package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.dto.WinnerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuctionService {

    private static final int BIDDING_TIME = 20;
    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ProviderService providerService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ContractInfo createAuction(RequirementsRequest requirementsRequest) throws Exception {
        Auction.Requirements requirements = this.requirementsRequestToAuctionRequirements(requirementsRequest);
        Auction auction = Auction.deploy(this.web3j, this.transactionManager, new DefaultGasProvider(), requirements,
                BigInteger.valueOf(BIDDING_TIME)).send();
        log.info("Auction smart contract deployed");
        this.scheduledExecutorService.schedule(() -> {
            try {
                auction.finishAuction().send();
                log.info("Auction finished successfully");
                this.simpMessagingTemplate.convertAndSend("/auction-finished/" + auction.getContractAddress(),
                        this.getWinner(auction.getContractAddress()));
            } catch (Exception e) {
                log.error("Failed to finish auction", e);
            }
        }, BIDDING_TIME, TimeUnit.SECONDS);
        ContractInfo contractInfo = ContractInfo.builder()
                .address(auction.getContractAddress())
                .ownerAddress(this.transactionManager.getFromAddress())
                .build();
        this.simpMessagingTemplate.convertAndSend("/auction-started", contractInfo);

        return contractInfo;
    }

    public WinnerInfo getWinner(String contractAddress) throws Exception {
        Auction auction = Auction.load(contractAddress, this.web3j, this.transactionManager, new DefaultGasProvider());
        Auction.WinnerInfo winnerInfo = auction.getWinner().send();
        return this.auctionWinnerInfoToWinnerInfo(winnerInfo);
    }

    private Auction.Requirements requirementsRequestToAuctionRequirements(RequirementsRequest requirementsRequest) {
        return new Auction.Requirements(requirementsRequest.getVnfName(), requirementsRequest.getVnfType(),
                BigInteger.valueOf(requirementsRequest.getNumCpus()), BigInteger.valueOf(requirementsRequest.getMemSize()));
    }

    private WinnerInfo auctionWinnerInfoToWinnerInfo(Auction.WinnerInfo winnerInfo) {
        ProviderInfo providerInfo = this.providerService.getProviderInfoByAddress(winnerInfo.bidderAddress);
        return WinnerInfo.builder().address(winnerInfo.bidderAddress).name(providerInfo.getName()).cost(winnerInfo.cost.intValue()).build();
    }

}
