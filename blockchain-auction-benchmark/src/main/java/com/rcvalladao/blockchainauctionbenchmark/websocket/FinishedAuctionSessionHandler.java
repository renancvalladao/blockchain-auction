package com.rcvalladao.blockchainauctionbenchmark.websocket;

import com.rcvalladao.blockchainauctionbenchmark.AuctionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class FinishedAuctionSessionHandler extends StompSessionHandlerAdapter {

    private StompSession session;
    private final Map<String, AuctionStatus> runningAuctions;

    @Override
    public void afterConnected(StompSession session, @NotNull StompHeaders connectedHeaders) {
        this.session = session;
    }

    public void subscribe(String auctionAddress) {
        this.session.subscribe("/auction-finished/" + auctionAddress, this);
    }

    @NotNull
    @Override
    public Type getPayloadType(@NotNull StompHeaders headers) {
        return Map.class;
    }

    @Override
    public void handleFrame(@NotNull StompHeaders headers, Object payload) {
        String destination = headers.getDestination();
        if (destination == null) return;
        int index = destination.lastIndexOf("/") + 1;
        String auctionAddress = destination.substring(index);
        AuctionStatus auctionStatus = this.runningAuctions.get(auctionAddress);
        auctionStatus.setFinishedAt(Instant.now());
        auctionStatus.setFinished(true);
    }

}
