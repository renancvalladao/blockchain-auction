package com.rcvalladao.blockchainauctionserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProviderController.class)
class ProviderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProviderService providerService;

    @Test
    void givenProviderInfo_whenCreateAuction_ReturnNoContent() throws Exception {
        ProviderInfo providerInfo = ProviderInfo.builder().name("Test").bidEndpoint("/test").build();

        this.mockMvc.perform(post("/providers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(providerInfo)))
                .andExpect(status().isNoContent());

        verify(this.providerService).addProvider(providerInfo);
    }

}