package com.tutorial.module11.caching.advices;

import com.tutorial.module11.caching.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String errorCode = ex.getCause() + "_NOT_FOUND";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCode + ex.getMessage());
    }
}
