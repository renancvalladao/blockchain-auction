package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuctionServiceTest {

    @Mock
    Web3j web3j;
    @Mock
    TransactionManager transactionManager;
    @Mock
    ScheduledExecutorService scheduledExecutorService;
    @Mock
    ProviderService providerService;
    @Mock
    SimpMessagingTemplate simpMessagingTemplate;
    @Mock
    RemoteCall<Auction> remoteCall;
    @Mock
    Auction auction;
    @Captor
    ArgumentCaptor<Auction.Requirements> requirementsArgumentCaptor;

    @Test
    void givenRequirements_whenCreateAuction_ThenDeployAuction() throws Exception {
        AuctionService auctionService = new AuctionService(this.web3j, this.transactionManager,
                this.scheduledExecutorService, this.providerService,this.simpMessagingTemplate);
        RequirementsRequest requirementsRequest = RequirementsRequest.builder()
                .vnfName("Name")
                .vnfType("Type")
                .numCpus(4)
                .build();
        Auction.Requirements expectedRequirements = new Auction.Requirements(requirementsRequest.getVnfName(),
                requirementsRequest.getVnfType(), BigInteger.valueOf(requirementsRequest.getNumCpus()),
                BigInteger.valueOf(requirementsRequest.getMemSize()));
        ContractInfo expectedContractInfo = ContractInfo.builder()
                .address("address")
                .ownerAddress(this.transactionManager.getFromAddress())
                .build();
        when(this.remoteCall.send()).thenReturn(this.auction);
        when(this.auction.getContractAddress()).thenReturn("address");

        ContractInfo actualContractInfo;
        try (MockedStatic<Auction> auctionStatic = Mockito.mockStatic(Auction.class)) {
            auctionStatic.when(() -> Auction.deploy(any(Web3j.class), any(TransactionManager.class), any(),
                            this.requirementsArgumentCaptor.capture(), any()))
                    .thenReturn(this.remoteCall);
            actualContractInfo = auctionService.createAuction(requirementsRequest);
        }

        verify(this.scheduledExecutorService).schedule(any(Runnable.class), eq(20L), eq(TimeUnit.SECONDS));
        verify(this.simpMessagingTemplate, times(1)).convertAndSend("/auction-started", expectedContractInfo);
        assertEquals(expectedRequirements, this.requirementsArgumentCaptor.getValue());
        assertEquals(expectedContractInfo, actualContractInfo);
    }

}