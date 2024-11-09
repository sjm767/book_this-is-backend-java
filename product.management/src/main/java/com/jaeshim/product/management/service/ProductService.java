package com.jaeshim.product.management.service;

import com.jaeshim.product.management.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto getProduct(Long id);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductByNameContains(String productName);

    ProductDto modifyProduct(Long id, ProductDto productDto);

    void remove(Long id);
}
