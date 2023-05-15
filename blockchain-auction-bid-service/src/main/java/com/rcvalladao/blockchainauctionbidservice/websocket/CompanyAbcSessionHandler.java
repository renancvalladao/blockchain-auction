package com.rcvalladao.blockchainauctionbidservice.websocket;

import com.rcvalladao.blockchainauctionbidservice.service.CompanyAbcBidService;
import com.rcvalladao.blockchainauctionserver.dto.ContractInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyAbcSessionHandler extends StompSessionHandlerAdapter {

    private final CompanyAbcBidService companyAbcBidService;

    @Override
    public void afterConnected(StompSession session, @NotNull StompHeaders connectedHeaders) {
        session.subscribe("/auction-started", this);
    }

    @NotNull
    @Override
    public Type getPayloadType(@NotNull StompHeaders headers) {
        return ContractInfo.class;
    }

    @Override
    public void handleFrame(@NotNull StompHeaders headers, Object payload) {
        ContractInfo contractInfo = (ContractInfo) payload;
        log.info("New auction at {}", contractInfo.getAddress());
        try {
            this.companyAbcBidService.placeBid(contractInfo);
        } catch (Exception e) {
            log.error("Could not place bid on contract at {}", contractInfo.getAddress(), e);
        }
    }

}
