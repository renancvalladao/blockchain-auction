package com.rcvalladao.blockchainauctionserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class GeneralConfiguration {

    @Bean
    ScheduledExecutorService scheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(2);
    }

}
