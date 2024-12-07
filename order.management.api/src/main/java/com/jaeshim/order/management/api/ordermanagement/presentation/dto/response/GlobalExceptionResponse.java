package com.jaeshim.order.management.api.ordermanagement.presentation.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GlobalExceptionResponse {
    private String message;
}
