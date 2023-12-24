package org.sdl.mintyn_bank_test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VerifyResponse(Payload payload){

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Payload payload;

        public Builder payload(Payload payload) {
            this.payload = payload;
            return this;
        }


        public VerifyResponse build() {
            return new VerifyResponse(payload);
        }
    }
}
