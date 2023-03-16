package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
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
    RemoteCall<Auction> remoteCall;
    @Mock
    Auction auction;
    @Captor
    ArgumentCaptor<Auction.Requirements> requirementsArgumentCaptor;

    @Test
    void givenRequirements_whenCreateAuction_ThenDeployAuction() throws Exception {
        ScheduledExecutorService scheduledExecutorService = mock(ScheduledExecutorService.class);
        AuctionService auctionService = new AuctionService(this.web3j, this.transactionManager, scheduledExecutorService);
        RequirementsRequest requirementsRequest = RequirementsRequest.builder()
                .vnfName("Name")
                .vnfType("Type")
                .numCpus(4)
                .build();
        Auction.Requirements expectedRequirements = new Auction.Requirements(requirementsRequest.getVnfName(),
                requirementsRequest.getVnfType(), BigInteger.valueOf(requirementsRequest.getNumCpus()));
        when(this.remoteCall.send()).thenReturn(this.auction);

        try (MockedStatic<Auction> auctionStatic = Mockito.mockStatic(Auction.class)) {
            auctionStatic.when(() -> Auction.deploy(any(Web3j.class), any(TransactionManager.class), any(),
                            this.requirementsArgumentCaptor.capture(), any()))
                    .thenReturn(this.remoteCall);
            auctionService.createAuction(requirementsRequest);
        }

        verify(scheduledExecutorService).schedule(any(Runnable.class), eq(10L), eq(TimeUnit.SECONDS));
        assertEquals(expectedRequirements, this.requirementsArgumentCaptor.getValue());
    }

}