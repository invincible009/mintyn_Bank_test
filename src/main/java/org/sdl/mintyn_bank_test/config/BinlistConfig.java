package org.sdl.mintyn_bank_test.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.sdl.mintyn_bank_test.http.BinlistClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

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

    @Bean
    public Bucket createBucket() {
        // Default rate limit (10 requests per minute)
        Bandwidth limit = Bandwidth.classic(10, Refill.of(10, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }
}
