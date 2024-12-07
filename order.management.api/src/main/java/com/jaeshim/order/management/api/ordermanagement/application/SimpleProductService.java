package com.jaeshim.order.management.api.ordermanagement.application;


import com.jaeshim.order.management.api.ordermanagement.domain.product.Product;
import com.jaeshim.order.management.api.ordermanagement.domain.product.ProductRepository;
import com.jaeshim.order.management.api.ordermanagement.presentation.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;

    @Autowired
    SimpleProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

}
