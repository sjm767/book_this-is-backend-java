package com.jaeshim.shorten.url.entity.error;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class Error {
    private int code;
    private String message;
    private String detail;
    private Instant time;
}
