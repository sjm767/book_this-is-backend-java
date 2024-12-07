package com.jaeshim.shorten.url.exception.handler;

import com.jaeshim.shorten.url.exception.LackOfShortenUrlKeyException;
import com.jaeshim.shorten.url.exception.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<String> handleNotFoundShortenUrlException(NotFoundShortenUrlException e) {
        return new ResponseEntity<>("단축 URL을 찾지 못했습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<String> LackOfShortenUrlKeyException(LackOfShortenUrlKeyException e) {
        return new ResponseEntity<>("단축 URL 자원이 부족합니다", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
