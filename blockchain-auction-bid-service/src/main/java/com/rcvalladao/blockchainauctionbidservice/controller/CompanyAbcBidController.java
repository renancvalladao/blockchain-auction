package com.rcvalladao.blockchainauctionbidservice.controller;

import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcBidService;
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
@RequestMapping("/company-abc")
public class CompanyAbcBidController {

    private final CompanyAbcBidService companyAbcBidService;

    @PostMapping("/bids")
    public ResponseEntity<Object> placeBid(@RequestBody ContractInfo contractInfo) throws Exception {
        log.info("New bid request for smart contract on " + contractInfo.getAddress());
        this.companyAbcBidService.placeBid(contractInfo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
