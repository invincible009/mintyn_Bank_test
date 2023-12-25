package org.sdl.mintyn_bank_test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BinlistResponse(Payload payload, String validCard){

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Payload payload;
        private String validCard;

        public Builder payload(Payload payload) {
            this.payload = payload;
            return this;
        }

        public Builder validCard(String validCard) {
            this.validCard = validCard;
            return this;
        }


        public BinlistResponse build() {
            return new BinlistResponse(payload,validCard);
        }
    }
}
