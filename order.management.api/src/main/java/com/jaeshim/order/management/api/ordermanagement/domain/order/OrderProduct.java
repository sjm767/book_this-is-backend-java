package com.jaeshim.order.management.api.ordermanagement.domain.order;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class OrderProduct {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
