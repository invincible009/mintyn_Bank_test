package org.sdl.mintyn_bank_test.service;

import org.sdl.mintyn_bank_test.dto.Payload;
import org.sdl.mintyn_bank_test.dto.VerifyResponse;
import org.sdl.mintyn_bank_test.http.BinlistClient;
import org.springframework.stereotype.Service;

@Service
public class BinlistService {
    private final BinlistClient binlistClient;

    public BinlistService(BinlistClient binlistClient) {
        this.binlistClient = binlistClient;
    }

    public VerifyResponse performLookup(String cardNumber){
        var lookupResponse = this.binlistClient.cardLookup(cardNumber);

        if(!lookupResponse.type().isBlank()){
            return VerifyResponse.builder()
                    .payload(Payload.builder()
                            .withScheme(lookupResponse.scheme())
                            .withType(lookupResponse.type())
                            .withBank(lookupResponse.bank().name())
                            .build())
                    .build();
        }
        return VerifyResponse.builder().build();
    }

}
