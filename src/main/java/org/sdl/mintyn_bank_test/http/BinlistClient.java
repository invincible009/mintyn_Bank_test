package org.sdl.mintyn_bank_test.http;

import org.sdl.mintyn_bank_test.dto.binlist.LookupResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface BinlistClient {
    @GetExchange("/{cardNumber}")
    LookupResponse cardLookup(@PathVariable String cardNumber);
}
