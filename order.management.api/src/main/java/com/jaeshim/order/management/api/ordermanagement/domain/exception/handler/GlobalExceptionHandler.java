package com.jaeshim.order.management.api.ordermanagement.domain.exception.handler;

import com.jaeshim.order.management.api.ordermanagement.domain.exception.EntityNotFoundException;
import com.jaeshim.order.management.api.ordermanagement.domain.exception.LackOfAmountException;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.response.GlobalExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        GlobalExceptionResponse res = GlobalExceptionResponse.builder().message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(LackOfAmountException.class)
    public ResponseEntity<GlobalExceptionResponse> handleLackOfAmountException(LackOfAmountException ex) {
        GlobalExceptionResponse res = GlobalExceptionResponse.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
