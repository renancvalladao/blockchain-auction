package com.rcvalladao.blockchainauctionserver.controller;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping("/providers")
    public ResponseEntity<Object> addProvider(@RequestBody ProviderInfo providerInfo) {
        log.info("New add provider request");
        this.providerService.addProvider(providerInfo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
