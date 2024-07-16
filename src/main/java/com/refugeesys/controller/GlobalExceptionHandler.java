package com.refugeesys.controller;

import com.refugeesys.controller.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // You can create a custom error response object if needed here
        // For demonstration, let's assume ErrorResponse is a custom class
        ErrorResponse errorResponse = new ErrorResponse() {
            @Override
            public HttpStatusCode getStatusCode() {
                return null;
            }
            @Override
            public ProblemDetail getBody() {
                return null;
            }
        };
        // Return ResponseEntity with the custom error response and HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
