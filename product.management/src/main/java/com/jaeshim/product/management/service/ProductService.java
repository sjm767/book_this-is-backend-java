package com.jaeshim.product.management.service;

import com.jaeshim.product.management.entity.Product;
import com.jaeshim.product.management.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    List<Product> getAllProducts();

}
