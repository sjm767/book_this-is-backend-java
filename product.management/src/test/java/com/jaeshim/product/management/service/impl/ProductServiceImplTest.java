package com.jaeshim.product.management.service.impl;

import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.entity.exception.EntityNotFoundException;
import com.jaeshim.product.management.repository.impl.ListProductRepository;
import com.jaeshim.product.management.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    ListProductRepository listProductRepository;

    @BeforeEach
    void beforeEach() {
        listProductRepository.clearAll();
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
        assertThat(result.getName()).isEqualTo("test");
        assertThat(result.getPrice()).isEqualTo(1000);
        assertThat(result.getAmount()).isEqualTo(99);
    }

    @Test
    @DisplayName("모든 Product를 조회한다")
    void findAllTest() {
        //given
        ProductDto product1 = ProductDto.builder().name("test").price(1000).amount(99).build();
        ProductDto product2 = ProductDto.builder().name("test").price(1000).amount(99).build();

        productService.addProduct(product1);
        productService.addProduct(product2);

        List<ProductDto> products = productService.getAllProducts();

        //then
        assertThat(products.size()).isEqualTo(2L);
    }

    @Test
    @DisplayName("id로 Product를 조회한다")
    void findByIdTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("test").price(1000).amount(99).build();
        ProductDto savedProduct = productService.addProduct(product1);

        // when
        ProductDto findProduct = productService.getProduct(savedProduct.getId());

        // then
        assertThat(findProduct.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("상품 이름에 포함된 문자열로 Product 조회하기")
    void findByNameContainsTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1_9999").price(1000).amount(99).build();
        ProductDto product2 = ProductDto.builder().name("상품2_9999").price(1000).amount(99).build();
        ProductDto product3 = ProductDto.builder().name("상품1_8888").price(1000).amount(99).build();
        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);

        // when
        List<ProductDto> products = productService.getProductByNameContains("상품1");

        // then
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).getName()).isEqualTo("상품1_9999");
        assertThat(products.get(1).getName()).isEqualTo("상품1_8888");
    }

    @Test
    @DisplayName("존재하는 상품 수정하기")
    void modifyValidTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1").price(1000).amount(99).build();
        ProductDto savedProduct = productService.addProduct(product1);

        // when
        ProductDto updatedProduct = productService.modifyProduct(savedProduct.getId(), ProductDto.builder().name("상품2").price(2000).amount(10).build());

        // then
        assertThat(updatedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(updatedProduct.getName()).isEqualTo("상품2");
    }

    @Test
    @DisplayName("존재하지 않는 상품 수정하기")
    void modifyInvalidTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1").price(1000).amount(99).build();
        ProductDto savedProduct = productService.addProduct(product1);

        // when, then
        assertThatThrownBy(() -> {
            productService.modifyProduct(999L, ProductDto.builder().name("상품2").price(2000).amount(10).build());
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("존재하는 상품 삭제하기")
    void deleteTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1").price(1000).amount(99).build();
        Long id = productService.addProduct(product1).getId();

        // when
        productService.remove(id);

        // then
        assertThatThrownBy(() -> {
            productService.getProduct(id);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 상품 삭제하기")
    void deleteInvalidTest() {
        // given
        Long id = 999L;

        // then
        assertThatThrownBy(() -> {
            productService.remove(id);
        }).isInstanceOf(EntityNotFoundException.class);
    }
}