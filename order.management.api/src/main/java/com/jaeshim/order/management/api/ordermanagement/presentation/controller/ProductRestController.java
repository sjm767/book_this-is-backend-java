package com.jaeshim.order.management.api.ordermanagement.presentation.controller;

import com.jaeshim.order.management.api.ordermanagement.application.SimpleProductService;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final SimpleProductService simpleProductService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts() {
        return simpleProductService.findAll();
    }

}
