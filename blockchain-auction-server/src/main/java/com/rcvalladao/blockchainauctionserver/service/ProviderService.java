package com.rcvalladao.blockchainauctionserver.service;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProviderService {

    private final Map<String, ProviderInfo> providersInfo = new HashMap<>();

    public void addProvider(ProviderInfo providerInfo) {
        this.providersInfo.put(providerInfo.getAddress(), providerInfo);
        log.info("New provider " + providerInfo.getName() + " registered");
    }

    public List<ProviderInfo> getProvidersInfo() {
        return this.providersInfo.values().stream().toList();
    }

    public ProviderInfo getProviderInfoByAddress(String address) {
        return this.providersInfo.get(address);
    }

}
