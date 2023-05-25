package com.rcvalladao.blockchainauctionbenchmark;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class BlockchainAuctionBenchmarkApplication implements CommandLineRunner {

    private final BenchmarkExecutor benchmarkExecutor;

    public static void main(String[] args) {
        SpringApplication.run(BlockchainAuctionBenchmarkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.benchmarkExecutor.run();
    }

}
