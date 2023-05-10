package com.rcvalladao.blockchainauctionbidservice.websocket;

import com.rcvalladao.blockchainauctionbidservice.service.CompanyDefBidService;
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
public class CompanyDefSessionHandler extends StompSessionHandlerAdapter {

    private final CompanyDefBidService companyDefBidService;

    @Override
    public void afterConnected(StompSession session, @NotNull StompHeaders connectedHeaders) {
        session.subscribe("/auction-notifier", this);
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
            this.companyDefBidService.placeBid(contractInfo);
        } catch (Exception e) {
            log.error("Could not place bid on contract at {}", contractInfo.getAddress(), e);
        }
    }

}
