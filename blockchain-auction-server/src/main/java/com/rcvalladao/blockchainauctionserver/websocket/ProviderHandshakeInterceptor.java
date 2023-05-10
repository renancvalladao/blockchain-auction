package com.rcvalladao.blockchainauctionserver.websocket;

import com.rcvalladao.blockchainauctionserver.dto.ProviderInfo;
import com.rcvalladao.blockchainauctionserver.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProviderHandshakeInterceptor implements HandshakeInterceptor {

    private final ProviderService providerService;

    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response,
                                   @NotNull WebSocketHandler wsHandler, @NotNull Map<String, Object> attributes) {
        return true;
    }

    @Override
    public void afterHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response,
                               @NotNull WebSocketHandler wsHandler, Exception exception) {
        HttpHeaders headers = request.getHeaders();
        List<String> addresses = headers.get("address");
        List<String> names = headers.get("name");

        if (addresses == null || names == null) {
            return;
        }

        Iterator<String> addressesIterator = addresses.iterator();
        Iterator<String> namesIterator = names.iterator();
        while (addressesIterator.hasNext() && namesIterator.hasNext()) {
            String address = addressesIterator.next();
            String name = namesIterator.next();
            this.providerService.addProvider(ProviderInfo.builder().name(name).address(address).build());
        }
    }

}
