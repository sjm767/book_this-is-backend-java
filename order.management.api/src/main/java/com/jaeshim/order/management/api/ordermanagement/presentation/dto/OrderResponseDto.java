package com.jaeshim.order.management.api.ordermanagement.presentation.dto;

import com.jaeshim.order.management.api.ordermanagement.domain.order.Order;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderResponseDto {
    private Order order;
}
