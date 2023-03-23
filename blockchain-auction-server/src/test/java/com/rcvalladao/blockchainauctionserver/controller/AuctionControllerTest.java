package com.rcvalladao.blockchainauctionserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.dto.WinnerInfo;
import com.rcvalladao.blockchainauctionserver.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuctionController.class)
class AuctionControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuctionService auctionService;

    @Test
    void givenRequirements_whenCreateAuction_ReturnNoContent() throws Exception {
        RequirementsRequest requirementsRequest = RequirementsRequest.builder()
                .vnfName("Name")
                .vnfType("Type")
                .numCpus(4)
                .build();
        ContractInfo contractInfo = ContractInfo.builder().address("address").ownerAddress("ownerAddress").build();
        when(this.auctionService.createAuction(any())).thenReturn(contractInfo);

        this.mockMvc.perform(post("/auctions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(requirementsRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address", is(contractInfo.getAddress())))
                .andExpect(jsonPath("$.ownerAddress", is(contractInfo.getOwnerAddress())));

        verify(this.auctionService).createAuction(requirementsRequest);
    }

    @Test
    void givenWinnerInfo_whenGetWinnerInfo_ReturnExpectedWinnerInfo() throws Exception {
        WinnerInfo expectedWinnerInfo = WinnerInfo.builder().address("address").cost(10).build();

        when(this.auctionService.getWinner(any())).thenReturn(expectedWinnerInfo);

        this.mockMvc.perform(get("/auctions/" + expectedWinnerInfo.getAddress() + "/winner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address", is(expectedWinnerInfo.getAddress())))
                .andExpect(jsonPath("$.cost", is(expectedWinnerInfo.getCost())));
    }

}