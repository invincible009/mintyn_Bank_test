package org.sdl.mintyn_bank_test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private T payload;
    private boolean success;

    public ApiResponse(boolean success, T payload ) {
        this.success = success;
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public boolean isSuccess() {
        return success;
    }
}
