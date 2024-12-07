package com.jaeshim.order.management.api.ordermanagement.domain.order;

import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Order {
    private Long id;
    private List<OrderProduct> orderedProducts;
    private Integer totalPrice;
    private OrderStatus status;

}
