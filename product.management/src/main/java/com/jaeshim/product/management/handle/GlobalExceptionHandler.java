package com.jaeshim.product.management.handle;

import com.jaeshim.product.management.entity.exception.EntityNotFoundException;
import com.jaeshim.product.management.entity.response.ErrorCode;
import com.jaeshim.product.management.entity.response.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(Exception ex) {
        ErrorMessage errorResponse = ErrorMessage.builder()
                .message("invalid argument")
                .time(Instant.now())
                .code(ErrorCode.INVALID_ARGUMENT.getCode())
                .detail(ErrorCode.INVALID_ARGUMENT.getDescription())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage errorResponse = ErrorMessage.builder()
                .message(ex.getMessage())
                .time(Instant.now())
                .code(ErrorCode.ENTITY_NOT_FOUND.getCode())
                .detail(ErrorCode.ENTITY_NOT_FOUND.getDescription())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
