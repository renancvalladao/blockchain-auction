package com.rcvalladao.blockchainauctionbidservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcvalladao.blockchainauctionbidservice.service.CompanyDefBidService;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyDefBidController.class)
class CompanyDefBidControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CompanyDefBidService companyDefBidService;

    @Test
    void givenContractInfo_whenPlaceBid_ReturnNoContent() throws Exception {
        ContractInfo contractInfo = ContractInfo.builder().address("address").ownerAddress("ownerAddress").build();

        this.mockMvc.perform(post("/company-def/bids")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(contractInfo)))
                .andExpect(status().isNoContent());

        verify(this.companyDefBidService).placeBid(contractInfo);
    }

}