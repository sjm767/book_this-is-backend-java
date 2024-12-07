package com.jaeshim.order.management.api.ordermanagement.listener;

import com.jaeshim.order.management.api.ordermanagement.application.SimpleOrderService;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitEventListener {

    private final SimpleOrderService orderService;

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshed() {
        List<OrderRequestDto> requests = List.of(
                new OrderRequestDto(1L, 1),
                new OrderRequestDto(3L, 1)
        );
        orderService.order(requests);
    }
}
