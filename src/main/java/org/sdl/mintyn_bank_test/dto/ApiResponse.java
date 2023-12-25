package org.sdl.mintyn_bank_test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(T payload, boolean success, int start, int limit, int size, String message) {

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }
    public static class Builder<T> {
        private T payload;
        private boolean success;
        private int start;
        private int limit;
        private int size;
        private String message;

        public Builder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> start(int start) {
            this.start = start;
            return this;
        }

        public Builder<T> limit(int limit) {
            this.limit = limit;
            return this;
        }
        public Builder<T> size(int size) {
            this.size = size;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }


        public ApiResponse<T> build() {
            return new ApiResponse<>(payload, success,start,limit,size,message);
        }
    }

}
