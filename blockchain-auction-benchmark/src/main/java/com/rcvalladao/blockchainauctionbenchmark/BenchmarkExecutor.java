package com.rcvalladao.blockchainauctionbenchmark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rcvalladao.blockchainauctionbenchmark.dto.AuctionResult;
import com.rcvalladao.blockchainauctionbenchmark.dto.BenchmarkResult;
import com.rcvalladao.blockchainauctionbenchmark.websocket.FinishedAuctionSessionHandler;
import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcBidService;
import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcCostService;
import com.rcvalladao.blockchainauctionbidservice.websocket.CompanyAbcSessionHandler;
import com.rcvalladao.blockchainauctionbidservice.websocket.CompanyAbcWebSocketClient;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import com.rcvalladao.blockchainauctionserver.dto.OptionalRequirement;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.service.AuctionService;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    private final Map<String, AuctionStatus> runningAuctions = new ConcurrentHashMap<>();
    @Value("${numberOfAuctions}")
    private int numberOfAuctions;
    @Value("${numberOfProviders}")
    private int numberOfProviders;
    @Value("${account.privateKey}")
    private String privateKey;
    private boolean error;


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

        for (int i = 0; i < this.numberOfProviders; i++) {
            CompanyAbcBidService companyBidService = new CompanyAbcBidService(this.web3j, this.transactionManager, new CompanyAbcCostService());
            CompanyAbcSessionHandler companySessionHandler = new CompanyAbcSessionHandler(companyBidService);
            CompanyAbcWebSocketClient companyWebSocketClient = new CompanyAbcWebSocketClient(companySessionHandler,
                    WEBSOCKET_URL + "/providers", this.privateKey);
            companyWebSocketClient.postConstruct();
        }

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(this.numberOfAuctions);

        FinishedAuctionSessionHandler finishedAuctionSessionHandler = new FinishedAuctionSessionHandler(this.runningAuctions);
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        stompClient.connectAsync(WEBSOCKET_URL + "/monitoring", headers, finishedAuctionSessionHandler);
        for (int i = 0; i < this.numberOfAuctions; i++) {
            executorService.schedule(() -> {
                try {
                    AuctionStatus auctionStatus = AuctionStatus.builder()
                            .startedAt(Instant.now())
                            .finished(false)
                            .build();
                    ContractInfo auction = auctionService.createAuction(requirementsRequest);
                    auctionStatus.setDeployedAt(Instant.now());
                    this.runningAuctions.put(auction.getAddress(), auctionStatus);
                    finishedAuctionSessionHandler.subscribe(auction.getAddress());
                } catch (Exception e) {
                    log.error("Failed to create auction", e);
                    this.error = true;
                }
            }, i, TimeUnit.SECONDS);
        }

        do {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (!this.auctionsFinished());

        if (this.error) {
            log.info("An error happened while running the benchmark");
            return;
        }

        this.writeResults();
        log.info("Benchmark finished");
    }

    private boolean auctionsFinished() {
        if (this.runningAuctions.isEmpty()) return false;
        return this.runningAuctions.values().stream().filter(auctionStatus -> !auctionStatus.isFinished()).toList().isEmpty();
    }

    private void writeResults() throws IOException {
        List<AuctionResult> resultList = new ArrayList<>();
        for (AuctionStatus auctionStatus : this.runningAuctions.values()) {
            long deployDuration = auctionStatus.getDeployedAt().toEpochMilli() - auctionStatus.getStartedAt().toEpochMilli();
            long auctionDuration = auctionStatus.getFinishedAt().toEpochMilli() - auctionStatus.getDeployedAt().toEpochMilli();
            AuctionResult benchmarkResult = AuctionResult.builder()
                    .deployDuration(deployDuration)
                    .auctionDuration(auctionDuration)
                    .build();
            resultList.add(benchmarkResult);
        }
        List<Long> auctionDurations = resultList.stream().map(AuctionResult::getAuctionDuration).toList();
        List<Long> deployDurations = resultList.stream().map(AuctionResult::getDeployDuration).toList();
        BenchmarkResult auctionResult = BenchmarkResult.builder()
                .auctionResults(resultList)
                .averageAuctionDuration(this.calculateAverage(auctionDurations))
                .averageDeployDuration(this.calculateAverage(deployDurations))
                .numberOfAuctions(this.numberOfAuctions)
                .numberOfProviders(this.numberOfProviders)
                .build();
        try (Writer writer = new FileWriter("output.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(auctionResult, writer);
        }
    }

    private long calculateAverage(List<Long> list) {
        long sum = 0;
        for (Long value : list) {
            sum += value;
        }

        return Math.round((double) sum / list.size());
    }

}
