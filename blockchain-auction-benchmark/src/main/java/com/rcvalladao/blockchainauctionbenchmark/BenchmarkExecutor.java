package com.rcvalladao.blockchainauctionbenchmark;

import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcBidService;
import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcCostService;
import com.rcvalladao.blockchainauctionbidservice.websocket.CompanyAbcSessionHandler;
import com.rcvalladao.blockchainauctionbidservice.websocket.CompanyAbcWebSocketClient;
import com.rcvalladao.blockchainauctionserver.dto.OptionalRequirement;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.service.AuctionService;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class BenchmarkExecutor {

    private static final String WEBSOCKET_URL = "ws://localhost:8083/benchmark";
    private final Web3j web3j;
    private final TransactionManager transactionManager;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ProviderService providerService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${numberOfAuctions}")
    private int numberOfAuctions;
    @Value("${numberOfProviders}")
    private int numberOfProviders;
    @Value("${account.privateKey}")
    private String privateKey;


    public void run() throws Exception {
        AuctionService auctionService = new AuctionService(this.web3j, this.transactionManager,
                this.scheduledExecutorService, this.providerService, this.simpMessagingTemplate);
        RequirementsRequest requirementsRequest = RequirementsRequest.builder()
                .vnfType("test")
                .vnfName("test")
                .numCpus(1)
                .memSize(1)
                .maxDelay(OptionalRequirement.builder().value(1).required(false).build())
                .bandwidth(OptionalRequirement.builder().value(1).required(false).build())
                .build();
        // N auctions
        CompanyAbcBidService companyAbcBidService = new CompanyAbcBidService(this.web3j, this.transactionManager, new CompanyAbcCostService());
        CompanyAbcSessionHandler companyAbcSessionHandler = new CompanyAbcSessionHandler(companyAbcBidService);
        CompanyAbcWebSocketClient companyAbcWebSocketClient = new CompanyAbcWebSocketClient(companyAbcSessionHandler,
                WEBSOCKET_URL, this.privateKey);
        companyAbcWebSocketClient.postConstruct();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(this.numberOfAuctions);
        for (int i = 0; i < this.numberOfAuctions; i++) {
            executorService.schedule(() -> auctionService.createAuction(requirementsRequest), i, TimeUnit.SECONDS);
        }

        // N providers
        for (int i = 0; i < this.numberOfProviders - 1; i++) {
            CompanyAbcBidService companyBidService = new CompanyAbcBidService(this.web3j, this.transactionManager, new CompanyAbcCostService());
            CompanyAbcSessionHandler companySessionHandler = new CompanyAbcSessionHandler(companyBidService);
            CompanyAbcWebSocketClient companyWebSocketClient = new CompanyAbcWebSocketClient(companySessionHandler,
                    WEBSOCKET_URL, this.privateKey);
            companyWebSocketClient.postConstruct();
        }

        auctionService.createAuction(requirementsRequest);
    }

}
