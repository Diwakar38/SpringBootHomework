package com.tutorial.SecurityApp.advices;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptions {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwtException(ExpiredJwtException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
    }
}
