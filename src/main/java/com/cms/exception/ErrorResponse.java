package com.cms.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private long timestamp;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

}
