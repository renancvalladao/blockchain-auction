package com.rcvalladao.blockchainauctionbidservice.service;

import com.rcvalladao.blockchainauctionserver.contract.Auction;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyAbcBidServiceTest {

    @Mock
    Web3j web3j;
    @Mock
    TransactionManager transactionManager;
    @Mock
    CompanyAbcCostService companyAbcCostService;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Auction auction;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void givenContractInfo_whenPlaceBid_ThenPlaceBidSuccessfully() throws Exception {
        CompanyAbcBidService companyAbcBidService = new CompanyAbcBidService(this.web3j, this.transactionManager,
                this.companyAbcCostService);
        ContractInfo contractInfo = ContractInfo.builder().address("address").build();
        when(this.auction.getRequirements().send()).thenReturn(new Auction.Requirements("name", "type", BigInteger.TWO, BigInteger.TWO));
        when(this.companyAbcCostService.calculateCost(anyInt(), anyInt())).thenReturn(4);

        try (MockedStatic<Auction> auctionStatic = Mockito.mockStatic(Auction.class)) {
            auctionStatic.when(() -> Auction.load(this.stringArgumentCaptor.capture(), any(Web3j.class),
                            any(TransactionManager.class), any()))
                    .thenReturn(this.auction);
            companyAbcBidService.placeBid(contractInfo);
        }

        verify(this.auction.getRequirements()).send();
        verify(this.auction.placeBid(BigInteger.valueOf(4))).send();
        verify(this.companyAbcCostService).calculateCost(2, 2);
        assertEquals(contractInfo.getAddress(), this.stringArgumentCaptor.getValue());
    }

}