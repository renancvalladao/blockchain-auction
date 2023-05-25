package com.rcvalladao.blockchainauctionbenchmark.configuration;

import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import com.rcvalladao.blockchainauctionserver.websocket.ProviderHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RequiredArgsConstructor
@Configuration
public class GeneralConfiguration {

    @Bean
    ScheduledExecutorService scheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Bean
    ProviderService providerService() {
        return new ProviderService();
    }

    @Bean
    ProviderHandshakeInterceptor providerHandshakeInterceptor(ProviderService providerService) {
        return new ProviderHandshakeInterceptor(providerService);
    }

}
