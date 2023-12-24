package org.sdl.mintyn_bank_test.dto;

public record Payload(String bank,String scheme, String type ){

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String bank;
        private String  scheme;
        private String type;

        public Builder withBank(String bank) {
            this.bank = bank;
            return this;
        }

        public Builder withScheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Payload build() {
            return new Payload(bank, scheme, type);
        }
    }
}
