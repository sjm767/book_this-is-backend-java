package com.jaeshim.product.management.repository.impl;

import com.jaeshim.product.management.entity.domain.Product;
import com.jaeshim.product.management.entity.exception.EntityNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class ListProductRepository implements com.jaeshim.product.management.repository.ProductRepository {
    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicLong productId = new AtomicLong();

    public Product save(Product product) {
        long id = productId.incrementAndGet();
        product.setId(id);
        products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return products;
    }

    public void clearAll() {
        products.clear();
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product 를 찾지 못했습니다"));
    }

    public List<Product> findByNameContains(String productName) {
        return products.stream()
                .filter(p -> p.isNameContains(productName))
                .toList();
    }

    public Product update(Long id, Product product) {
        products.set(products.indexOf(product), product);
        return product;
    }

    public void deleteById(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
