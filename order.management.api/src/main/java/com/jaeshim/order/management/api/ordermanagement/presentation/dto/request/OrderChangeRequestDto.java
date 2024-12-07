package com.jaeshim.order.management.api.ordermanagement.presentation.dto.request;

import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import lombok.Data;

@Data
public class OrderChangeRequestDto {
    private OrderStatus state;
}
