package com.jaeshim.order.management.api.ordermanagement.presentation.controller;

import com.jaeshim.order.management.api.ordermanagement.application.SimpleOrderService;
import com.jaeshim.order.management.api.ordermanagement.domain.constatns.OrderStatus;
import com.jaeshim.order.management.api.ordermanagement.domain.order.Order;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderChangeRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.request.OrderRequestDto;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final SimpleOrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderResponseDto> order(@RequestBody List<OrderRequestDto> orderRequests) {
        OrderResponseDto response = orderService.order(orderRequests);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> orderStatus(@PathVariable Long orderId, @RequestBody OrderChangeRequestDto request) {
        OrderResponseDto response = orderService.changeOrderStatus(orderId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponseDto> order(@PathVariable Long orderId) {
        OrderResponseDto response = orderService.getOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderResponseDto>> orders(@RequestParam OrderStatus state) {
        List<OrderResponseDto> response = orderService.getOrdersByOrderStatus(state);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
