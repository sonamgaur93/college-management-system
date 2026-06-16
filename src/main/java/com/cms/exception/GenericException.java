package com.cms.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericException extends RuntimeException {

    private String message;
    private HttpStatus statusCode;

    public GenericException(String message, HttpStatus statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public GenericException(String message) {
        super(message);
        this.message = message;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
