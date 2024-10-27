package com.jaeshim.product.management.repository;

import com.jaeshim.product.management.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
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
}
