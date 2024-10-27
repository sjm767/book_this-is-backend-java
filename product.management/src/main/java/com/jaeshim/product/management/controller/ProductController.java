package com.jaeshim.product.management.controller;

import com.jaeshim.product.management.entity.Product;
import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    public List<Product> getProducts() {


        return productService.getAllProducts();
    }

    @PostMapping(value = "/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }
}
