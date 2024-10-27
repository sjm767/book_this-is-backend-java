package com.jaeshim.product.management.service.impl;

import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.entity.Product;
import com.jaeshim.product.management.repository.ProductRepository;
import com.jaeshim.product.management.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        productRepository.clearAll();
    }

    @Test
    @DisplayName("Product를 생성한다")
    void addTest() {
        //given
        ProductDto product = ProductDto.builder()
                .name("test")
                .price(1000)
                .amount(99)
                .build();
        //when
        ProductDto result = productService.addProduct(product);

        //then
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("모든 Product를 조회한다")
    void findAllTest() {
        //given
        ProductDto product1 = ProductDto.builder().name("test").price(1000).amount(99).build();
        ProductDto product2 = ProductDto.builder().name("test").price(1000).amount(99).build();

        productService.addProduct(product1);
        productService.addProduct(product2);

        List<Product> allProducts = productService.getAllProducts();

        //then
        assertThat(allProducts.size()).isEqualTo(2L);
    }
}