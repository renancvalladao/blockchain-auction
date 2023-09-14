package com.rcvalladao.blockchainauctionbenchmark.configuration;

import com.rcvalladao.blockchainauctionserver.websocket.ProviderHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final ProviderHandshakeInterceptor providerHandshakeInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/benchmark/providers").addInterceptors(this.providerHandshakeInterceptor);
        registry.addEndpoint("/benchmark/monitoring");
    }

}
