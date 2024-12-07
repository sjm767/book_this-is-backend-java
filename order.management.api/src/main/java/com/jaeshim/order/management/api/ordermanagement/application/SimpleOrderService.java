package com.jaeshim.order.management.api.ordermanagement.application;


import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import com.jaeshim.order.management.api.ordermanagement.domain.exception.EntityNotFoundException;
import com.jaeshim.order.management.api.ordermanagement.domain.exception.LackOfAmountException;
import com.jaeshim.order.management.api.ordermanagement.domain.order.Order;
import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderProduct;
import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderRepository;
import com.jaeshim.order.management.api.ordermanagement.domain.product.Product;
import com.jaeshim.order.management.api.ordermanagement.domain.product.ProductRepository;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderChangeRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDto order(List<OrderRequestDto> orderRequests) {
        List<OrderProduct> orderProducts = orderRequests.stream().map(orderRequest -> {
            Product product = productRepository.findById(orderRequest.getId());
            if (product.getAmount() - orderRequest.getAmount() < 0) {
                throw new LackOfAmountException(orderRequest.getId() + "번 상품의 수량이 부족합니다.");
            }
            return generateOrderProduct(orderRequest, product);
        }).toList();

        Order order = orderRepository.addOrder(orderProducts);
        return this.buildOrderResponseDto(order);
    }

    public OrderResponseDto changeOrderStatus(Long orderId, OrderChangeRequestDto request) {
        Order order = this.findOrderOrThrow(orderId);
        order.setStatus(request.getState());

        Order update = orderRepository.update(order);
        return this.buildOrderResponseDto(update);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order findOrder = this.findOrderOrThrow(orderId);
        return this.buildOrderResponseDto(findOrder);
    }

    public void removeAll() {
        orderRepository.deleteAll();
    }

    public List<OrderResponseDto> getOrdersByOrderStatus(OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(order -> OrderResponseDto.builder().order(order).build())
                .toList();
    }

    private OrderProduct generateOrderProduct(OrderRequestDto request, Product product) {
        return OrderProduct.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .amount(request.getAmount())
                .build();
    }

    private Order findOrderOrThrow(Long orderId) {
        Order order = orderRepository.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Order를 찾지 못했습니다.");
        }
        return order;
    }

    private OrderResponseDto buildOrderResponseDto(Order order) {
        return OrderResponseDto.builder().order(order).build();
    }

}
