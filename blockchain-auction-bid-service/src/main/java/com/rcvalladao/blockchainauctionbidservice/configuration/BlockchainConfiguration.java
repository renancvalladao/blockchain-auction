package com.rcvalladao.blockchainauctionbidservice.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

import java.net.ConnectException;

@Configuration
public class BlockchainConfiguration {

    @Bean(destroyMethod = "shutdown")
    Web3j web3j(@Value("${node.websocketUrl}") String nodeWebsocketUrl) throws ConnectException {
        WebSocketService webSocketService = new WebSocketService(nodeWebsocketUrl, true);
        webSocketService.connect();
        return Web3j.build(webSocketService);
    }

    @Qualifier("companyAbcTransactionManager")
    @Bean
    TransactionManager companyAbcTransactionManager(@Value("${companyAbc.privateKey}") String privateKey,
                                                    @Value("${chain.id}") int chainId, Web3j web3j) {
        Credentials credentials = Credentials.create(privateKey);
        return new RawTransactionManager(web3j, credentials, chainId);
    }

    @Qualifier("companyDefTransactionManager")
    @Bean
    TransactionManager companyDefTransactionManager(@Value("${companyDef.privateKey}") String privateKey,
                                                    @Value("${chain.id}") int chainId, Web3j web3j) {
        Credentials credentials = Credentials.create(privateKey);
        return new RawTransactionManager(web3j, credentials, chainId);
    }

}
