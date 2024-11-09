package com.jaeshim.product.management.repository;

import com.jaeshim.product.management.entity.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    List<Product> findAll();

    void clearAll();

    Product findById(Long id);

    List<Product> findByNameContains(String productName);

    Product update(Long id, Product product);

    void deleteById(Long id);
}
