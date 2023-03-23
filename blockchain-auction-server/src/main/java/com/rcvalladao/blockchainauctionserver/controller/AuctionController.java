package com.rcvalladao.blockchainauctionserver.controller;

import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import com.rcvalladao.blockchainauctionserver.dto.RequirementsRequest;
import com.rcvalladao.blockchainauctionserver.dto.WinnerInfo;
import com.rcvalladao.blockchainauctionserver.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/auctions")
    public ResponseEntity<ContractInfo> createAuction(@RequestBody RequirementsRequest requirementsRequest) throws Exception {
        log.info("New create auction request");
        ContractInfo contractInfo = this.auctionService.createAuction(requirementsRequest);
        return new ResponseEntity<>(contractInfo, HttpStatus.CREATED);
    }

    @GetMapping("/auctions/{contractAddress}/winner")
    public WinnerInfo getWinnerInfo(@PathVariable("contractAddress") String contractAddress) throws Exception {
        return this.auctionService.getWinner(contractAddress);
    }

}
