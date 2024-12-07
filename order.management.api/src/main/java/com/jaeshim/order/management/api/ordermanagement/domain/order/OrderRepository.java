package com.jaeshim.order.management.api.ordermanagement.domain.order;

import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;

import java.util.List;

public interface OrderRepository {

    Order addOrder(List<OrderProduct> orderedProducts);

    Order find(Long id);

    List<Order> findByStatus(OrderStatus status);

    Order update(Order order);

    void deleteAll();
}
