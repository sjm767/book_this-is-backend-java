package com.jaeshim.order.management.api.ordermanagement.infrastructure;


import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import com.jaeshim.order.management.api.ordermanagement.domain.order.Order;
import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderRepository;
import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListOrderRepository implements OrderRepository {

    private static final Long INIT_SEQUENCE = 1L;

    private final ConcurrentHashMap<Long, Order> orderMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(INIT_SEQUENCE);

    @Override
    public Order addOrder(List<OrderProduct> orderedProducts) {
        long orderId = sequence.getAndIncrement();
        int totalPrice = orderedProducts.stream().mapToInt(OrderProduct::getPrice).sum();

        Order newOrder = Order.builder()
                .id(orderId)
                .orderedProducts(orderedProducts)
                .totalPrice(totalPrice)
                .status(OrderStatus.CREATED)
                .build();

        orderMap.put(orderId, newOrder);
        return newOrder;
    }

    @Override
    public Order find(Long id) {
        return orderMap.get(id);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return orderMap.values().stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }

    @Override
    public Order update(Order order) {
        return orderMap.put(order.getId(), order);
    }

    @Override
    public void deleteAll() {
        orderMap.clear();
        sequence.set(INIT_SEQUENCE);
    }


}
