package com.cms.exception.handler;

import com.cms.exception.ErrorResponse;
import com.cms.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                ex.getStatusCode()
        );
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
