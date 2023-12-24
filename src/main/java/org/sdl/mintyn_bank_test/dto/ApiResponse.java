package org.sdl.mintyn_bank_test.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse <T>{
    private T data;
    private HttpStatus httpStatus;
    private boolean successful;

    public ApiResponse(T data, HttpStatus httpStatus, boolean successful) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.successful = successful;
    }

    public T getData() {
        return data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public boolean isSuccessful() {
        return successful;
    }

}
