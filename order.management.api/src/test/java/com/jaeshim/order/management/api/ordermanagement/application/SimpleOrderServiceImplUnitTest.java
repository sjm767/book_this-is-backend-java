package com.jaeshim.order.management.api.ordermanagement.application;

import com.jaeshim.order.management.api.ordermanagement.domain.order.OrderRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleOrderServiceImplUnitTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    SimpleOrderService orderService;

}
