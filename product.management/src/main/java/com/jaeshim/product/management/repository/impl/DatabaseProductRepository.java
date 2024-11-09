package com.jaeshim.product.management.repository.impl;

import com.jaeshim.product.management.entity.domain.Product;
import com.jaeshim.product.management.repository.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public class DatabaseProductRepository implements ProductRepository {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void clearAll() {

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findByNameContains(String productName) {
        return List.of();
    }

    @Override
    public Product update(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
