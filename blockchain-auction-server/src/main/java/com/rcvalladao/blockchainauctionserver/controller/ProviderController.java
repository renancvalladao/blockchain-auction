package com.rcvalladao.blockchainauctionserver.controller;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/providers")
    public List<ProviderInfo> getProviders() {
        return this.providerService.getProvidersInfo();
    }

}
