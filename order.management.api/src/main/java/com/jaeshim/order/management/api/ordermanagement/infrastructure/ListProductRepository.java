package com.jaeshim.order.management.api.ordermanagement.infrastructure;

import com.jaeshim.order.management.api.ordermanagement.domain.exception.EntityNotFoundException;
import com.jaeshim.order.management.api.ordermanagement.domain.product.Product;
import com.jaeshim.order.management.api.ordermanagement.domain.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ListProductRepository implements ProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();

    @PostConstruct
    void initProducts() {
        Product product1 = new Product(1L, "상품1", 10000, 100);
        Product product2 = new Product(2L, "상품2", 25000, 300);
        Product product3 = new Product(3L, "상품3", 30000, 500);

        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다."));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
    }

}
