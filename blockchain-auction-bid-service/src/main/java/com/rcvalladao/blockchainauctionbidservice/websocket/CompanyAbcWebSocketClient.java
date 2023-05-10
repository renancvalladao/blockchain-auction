package com.rcvalladao.blockchainauctionbidservice.websocket;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.web3j.crypto.Credentials;

@Component
public class CompanyAbcWebSocketClient {

    private final CompanyAbcSessionHandler companyAbcSessionHandler;
    private final String serverWebsocketUrl;
    private final String privateKey;

    public CompanyAbcWebSocketClient(CompanyAbcSessionHandler companyAbcSessionHandler,
                                     @Value("${auctionServer.websocket}") String serverWebsocketUrl,
                                     @Value("${companyAbc.privateKey}") String privateKey) {
        this.companyAbcSessionHandler = companyAbcSessionHandler;
        this.serverWebsocketUrl = serverWebsocketUrl;
        this.privateKey = privateKey;
    }

    @PostConstruct
    public void postConstruct() {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        Credentials credentials = Credentials.create(this.privateKey);
        headers.add("address", credentials.getAddress());
        headers.add("name", "Company ABC");
        stompClient.connectAsync(this.serverWebsocketUrl, headers, this.companyAbcSessionHandler);
    }

}
