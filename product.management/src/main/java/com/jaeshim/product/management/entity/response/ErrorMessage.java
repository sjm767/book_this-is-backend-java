package com.jaeshim.product.management.entity.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ErrorMessage {
    private int code;
    private String message;
    private String detail;
    private Instant time;
}
