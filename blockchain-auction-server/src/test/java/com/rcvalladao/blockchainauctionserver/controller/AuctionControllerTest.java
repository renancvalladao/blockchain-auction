package com.rcvalladao.blockchainauctionserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        this.mockMvc.perform(post("/auctions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(requirementsRequest)))
                .andExpect(status().isNoContent());

        verify(this.auctionService).createAuction(requirementsRequest);
    }

}