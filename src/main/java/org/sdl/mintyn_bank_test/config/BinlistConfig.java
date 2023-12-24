package org.sdl.mintyn_bank_test.config;

import org.sdl.mintyn_bank_test.http.BinlistClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class BinlistConfig {

    @Value("${app.binlist.api.domain}")
    private String domain;
    @Bean
    BinlistClient binlistClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(domain)
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(BinlistClient.class);
    }
}
