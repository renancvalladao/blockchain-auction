package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuctionService {

    private static final Duration BIDDING_TIME = Duration.ofSeconds(30);
    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ProviderService providerService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ContractInfo createAuction(RequirementsRequest requirementsRequest) throws Exception {
        Auction.Requirements requirements = this.requirementsRequestToAuctionRequirements(requirementsRequest);
        Auction auction = Auction.deploy(this.web3j, this.transactionManager, new DefaultGasProvider(), requirements,
                BigInteger.valueOf(BIDDING_TIME.getSeconds())).send();
        log.info("Auction smart contract deployed " + auction.getContractAddress());
        this.scheduledExecutorService.schedule(() -> {
            auction.finishAuction().sendAsync().thenAccept(action -> {
                log.info("Auction finished successfully " + auction.getContractAddress());
                try {
                    this.simpMessagingTemplate.convertAndSend("/auction-finished/" + auction.getContractAddress(),
                            this.getResult(auction.getContractAddress()));
                } catch (Exception e) {
                    log.error("Failed to finish auction", e);
                }
            });

        }, BIDDING_TIME.getSeconds(), TimeUnit.SECONDS);
        ContractInfo contractInfo = ContractInfo.builder()
                .address(auction.getContractAddress())
                .ownerAddress(this.transactionManager.getFromAddress())
                .build();
        this.simpMessagingTemplate.convertAndSend("/auction-started", contractInfo);

        return contractInfo;
    }

    private Map<String, Object> getResult(String contractAddress) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("winnerInfo", this.getWinner(contractAddress));
        result.put("bidHistory", this.getBidHistory(contractAddress));
        return result;
    }

    private WinnerInfo getWinner(String contractAddress) throws Exception {
        Auction auction = Auction.load(contractAddress, this.web3j, this.transactionManager, new DefaultGasProvider());
        Auction.WinnerInfo winnerInfo = auction.getWinner().send();
        return this.auctionWinnerInfoToWinnerInfo(winnerInfo);
    }

    private List<Bid> getBidHistory(String contractAddress) throws Exception {
        Auction auction = Auction.load(contractAddress, this.web3j, this.transactionManager, new DefaultGasProvider());
        List<Auction.Bid> bidHistory = auction.getBidHistory().send();
        return bidHistory.stream().map(this::auctionBidToBid).collect(Collectors.toList());
    }

    private Auction.Requirements requirementsRequestToAuctionRequirements(RequirementsRequest requirementsRequest) {
        return new Auction.Requirements(requirementsRequest.getVnfName(), requirementsRequest.getVnfType(),
                BigInteger.valueOf(requirementsRequest.getNumCpus()), BigInteger.valueOf(requirementsRequest.getMemSize()),
                this.optionalRequirementToAuctionOptionalRequirement(requirementsRequest.getMaxDelay()),
                this.optionalRequirementToAuctionOptionalRequirement(requirementsRequest.getBandwidth()));
    }

    private Auction.OptionalRequirement optionalRequirementToAuctionOptionalRequirement(OptionalRequirement optionalRequirement) {
        return new Auction.OptionalRequirement(BigInteger.valueOf(optionalRequirement.getValue()), optionalRequirement.isRequired());
    }

    private WinnerInfo auctionWinnerInfoToWinnerInfo(Auction.WinnerInfo winnerInfo) {
        ProviderInfo providerInfo = this.providerService.getProviderInfoByAddress(winnerInfo.bidderAddress);
        return WinnerInfo.builder().address(winnerInfo.bidderAddress).name(providerInfo.getName()).cost(winnerInfo.cost.intValue()).build();
    }

    private Bid auctionBidToBid(Auction.Bid bid) {
        return Bid.builder().value(bid.value.intValue()).bidder(bid.bidder).build();
    }

}
