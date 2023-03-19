package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProviderService {

    private final List<ProviderInfo> providersInfo = new ArrayList<>();

    public void addProvider(ProviderInfo providerInfo) {
        this.providersInfo.add(providerInfo);
        log.info("New provider " + providerInfo.getName() + " endpoint registered");
    }

    public List<ProviderInfo> getProvidersInfo() {
        return this.providersInfo;
    }

}
