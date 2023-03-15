package com.rcvalladao.blockchainauctionbidservice.controller;

import com.rcvalladao.blockchainauctionbidservice.service.CompanyDefBidService;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/company-def")
public class CompanyDefBidController {

    private final CompanyDefBidService companyDefBidService;

    @PostMapping("/bids")
    public ResponseEntity<Object> placeBid(@RequestBody ContractInfo contractInfo) {
        log.info("New bid request for smart contract on " + contractInfo.getAddress());
        this.companyDefBidService.placeBid(contractInfo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
