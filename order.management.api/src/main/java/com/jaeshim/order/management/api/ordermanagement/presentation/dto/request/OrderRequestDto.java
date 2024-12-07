package com.jaeshim.order.management.api.ordermanagement.presentation.dto.request;

import lombok.Data;

@Data
public class OrderRequestDto {
    private final Long id;
    private final Integer amount;
}
