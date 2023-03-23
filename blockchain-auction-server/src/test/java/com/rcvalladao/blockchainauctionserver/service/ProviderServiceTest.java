package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProviderServiceTest {

    @Test
    void givenProviderInfo_whenAddProvider_ThenReturnAddedProviderInfo() {
        ProviderService providerService = new ProviderService();
        ProviderInfo providerInfo = ProviderInfo.builder().name("name").bidEndpoint("endpoint").build();

        providerService.addProvider(providerInfo);
        List<ProviderInfo> providersInfo = providerService.getProvidersInfo();

        assertEquals(1, providersInfo.size());
        assertEquals(providerInfo, providersInfo.get(0));
    }

}