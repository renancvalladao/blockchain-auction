package com.rcvalladao.blockchainauctionserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

@Configuration
public class BlockchainConfiguration {

    @Bean(destroyMethod = "shutdown")
    Web3j web3j(@Value("${node.url}") String nodeUrl) {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean
    TransactionManager transactionManager(@Value("${account.privateKey}") String privateKey,
                                          @Value("${chain.id}") int chainId, Web3j web3j) {
        Credentials credentials = Credentials.create(privateKey);
        return new RawTransactionManager(web3j, credentials, chainId);
    }

}
