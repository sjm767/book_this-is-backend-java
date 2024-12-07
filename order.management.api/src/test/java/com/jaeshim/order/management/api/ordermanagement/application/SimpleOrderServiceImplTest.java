package com.jaeshim.order.management.api.ordermanagement.application;


import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import com.jaeshim.order.management.api.ordermanagement.domain.exception.EntityNotFoundException;
import com.jaeshim.order.management.api.ordermanagement.domain.exception.LackOfAmountException;
import com.jaeshim.order.management.api.ordermanagement.domain.order.Order;
import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderProduct;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderChangeRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.OrderResponseDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class SimpleOrderServiceImplTest {

    @Autowired
    SimpleOrderService orderService;

    @AfterEach
    void afterEach() {
        orderService.removeAll();
    }

    @Test
    @DisplayName("주문 생성 성공")
    void OrderSuccess() {
        // given
        List<OrderRequestDto> requests = List.of(
                new OrderRequestDto(1L, 1),
                new OrderRequestDto(3L, 1)
        );
        OrderResponseDto expect = OrderResponseDto.builder()
                .order(Order.builder()
                        .id(1L)
                        .orderedProducts(List.of(
                                OrderProduct.builder().id(1L).name("상품1").price(10000).amount(1).build(),
                                OrderProduct.builder().id(3L).name("상품3").price(30000).amount(1).build()
                        ))
                        .status(OrderStatus.CREATED)
                        .totalPrice(40000).build()
                )
                .build();

        // when
        OrderResponseDto actual = orderService.order(requests);

        // then
        assertThat(actual).isEqualTo(expect);
        assertThat(actual.getOrder().getOrderedProducts().getFirst().getName()).isEqualTo("상품1");
        assertThat(actual.getOrder().getOrderedProducts().getFirst().getPrice()).isEqualTo(10000);

    }

    @Test
    @DisplayName("주문 수량 부족")
    void OrderFailLackOfAmountException() {
        // given
        List<OrderRequestDto> requests = List.of(
                new OrderRequestDto(1L, 99999),
                new OrderRequestDto(3L, 1)
        );

        // when, then
        assertThatThrownBy(() -> {
            orderService.order(requests);
        }).isInstanceOf(LackOfAmountException.class);
    }

    @Test
    @DisplayName("주문 실패 - (Product 찾지 못함)")
    void OrderFailNotFoundEntityException() {
        // given
        List<OrderRequestDto> requests = List.of(
                new OrderRequestDto(99999L, 1),
                new OrderRequestDto(3L, 1)
        );

        // when
        assertThatThrownBy(() -> {
            orderService.order(requests);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("주문 상태 강제 변경 - 성공")
    void changeOrderStatus() {
        // given
        List<OrderRequestDto> requests = List.of(new OrderRequestDto(1L, 1));
        orderService.order(requests);
        OrderChangeRequestDto request = new OrderChangeRequestDto();
        request.setState(OrderStatus.SHIPPING);

        // when
        OrderResponseDto actual = orderService.changeOrderStatus(1L, request);

        // then
        assertThat(actual.getOrder().getStatus()).isEqualTo(OrderStatus.SHIPPING);
    }

    @Test
    @DisplayName("주문 상태 강제 변경 - 실패 (Order 찾지 못함")
    void changeOrderFail() {
        // given
        OrderChangeRequestDto request = new OrderChangeRequestDto();
        request.setState(OrderStatus.SHIPPING);

        // when, then
        assertThatThrownBy(() -> {
            orderService.changeOrderStatus(1L, request);
        }).isInstanceOf(EntityNotFoundException.class);
    }


}