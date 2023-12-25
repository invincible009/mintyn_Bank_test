package org.sdl.mintyn_bank_test.service;

import io.github.bucket4j.*;
import org.sdl.mintyn_bank_test.dto.Payload;
import org.sdl.mintyn_bank_test.dto.BinlistResponse;
import org.sdl.mintyn_bank_test.http.BinlistClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BinlistService {
    private final BinlistClient binlistClient;
    private Bucket bucket;

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public BinlistService(BinlistClient binlistClient) {
        this.binlistClient = binlistClient;
    }

    public BinlistResponse performLookup(String cardNumber){
        var lookupResponse = this.binlistClient.cardLookup(cardNumber);

        if(!lookupResponse.type().isBlank()){
            return BinlistResponse.builder()
                    .payload(Payload.builder()
                            .withScheme(lookupResponse.scheme())
                            .withType(lookupResponse.type())
                            .withBank(lookupResponse.bank().name())
                            .build())
                    .build();
        }
        return BinlistResponse.builder().build();
    }


    public boolean isAllowed(String clientId, int start, int limit) {
        Bandwidth limitConfig = Bandwidth.classic(limit, Refill.of(limit, Duration.ofMinutes(start)));
        Bucket dynamicBucket = Bucket4j.builder().addLimit(limitConfig).build();

        Bucket bucket = buckets.computeIfAbsent(clientId, key -> dynamicBucket);

        return bucket.tryConsume(1);
    }


    public BinlistResponse getCardInfo() {
        //Call the Binlist client and get card information
        return BinlistResponse.builder()
                .validCard("This Card is still valid")
                .payload(Payload.builder().withBank("ABC").build())
                .build();
    }
}
