package com.jaeshim.product.management.service.impl;


import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.entity.domain.Product;
import com.jaeshim.product.management.repository.ProductRepository;
import com.jaeshim.product.management.service.ValidationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ValidationService validationService;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @DisplayName("Product를 생성한다")
    void addTest() {
        //given
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("test")
                .price(1000)
                .amount(99)
                .build();

        Product product = ProductDto.convertToProduct(productDto);
        when(productRepository.save(any())).thenReturn(product);

        //when
        ProductDto savedProduct = productService.addProduct(productDto);

        //then
        assertThat(savedProduct.getId()).isEqualTo(1L);
        assertThat(savedProduct.getName()).isEqualTo("test");
        assertThat(savedProduct.getPrice()).isEqualTo(1000);
        assertThat(savedProduct.getAmount()).isEqualTo(99);
    }

    @Test
    @DisplayName("모든 Product를 조회한다")
    void findAllTest() {
        // given
        ProductDto product1 = ProductDto.builder().id(1L).name("test").price(1000).amount(99).build();
        ProductDto product2 = ProductDto.builder().id(2L).name("test").price(1000).amount(99).build();

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Product> list = List.of(
                ProductDto.convertToProduct(productService.addProduct(product1)),
                ProductDto.convertToProduct(productService.addProduct(product2))
        );
        when(productRepository.findAll()).thenReturn(list);
        List<ProductDto> products = productService.getAllProducts();

        //then
        assertThat(products.size()).isEqualTo(2L);
    }

    @Test
    @DisplayName("id로 Product를 조회한다")
    void findByIdTest() {
        // given
        ProductDto product1 = ProductDto.builder().id(1L).name("test").price(1000).amount(99).build();
        Product product = ProductDto.convertToProduct(product1);

        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findById(1L)).thenReturn(product);

        // when
        ProductDto savedProduct = productService.addProduct(product1);
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

        Product entity1 = ProductDto.convertToProduct(product1);
        Product entity2 = ProductDto.convertToProduct(product2);
        Product entity3 = ProductDto.convertToProduct(product3);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(productRepository.findByNameContains(any(String.class))).thenAnswer(invocation -> {
            String productName = invocation.getArgument(0);
            if ("상품1".equals(productName)) {
                return Arrays.asList(entity1, entity3);
            } else if ("상품2".equals(productName)) {
                return Collections.singletonList(entity2);
            }
            return List.of();
        });

        // when
        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);

        List<ProductDto> products = productService.getProductByNameContains("상품1");

        // then
        verify(productRepository, times(3)).save(any(Product.class));
        verify(productRepository, times(1)).findByNameContains(any(String.class));

        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).getName()).isEqualTo("상품1_9999");
        assertThat(products.get(1).getName()).isEqualTo("상품1_8888");
    }

    @Test
    @DisplayName("존재하는 상품 수정하기")
    void modifyValidTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1").price(1000).amount(99).build();

        Product org = Product.builder().id(1L).name("상품1").price(1000).amount(99).build();
        Product updated = Product.builder().id(1L).name("상품2").price(2000).amount(10).build();

        when(productRepository.save(any(Product.class))).thenReturn(org);
        when(productRepository.update(any(Long.class), any(Product.class))).thenReturn(updated);

        // when
        ProductDto savedProduct = productService.addProduct(product1);
        ProductDto updatedProduct = productService.modifyProduct(savedProduct.getId(), ProductDto.builder().name("상품2").price(2000).amount(10).build());

        // then
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productRepository, times(1)).update(any(Long.class), any(Product.class));

        assertThat(updatedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(updatedProduct.getName()).isEqualTo("상품2");
    }

    @Test
    @DisplayName("존재하지 않는 상품 수정하기")
    void modifyInvalidTest() {
        // given
        ProductDto product1 = ProductDto.builder().name("상품1").price(1000).amount(99).build();
        ProductDto product2 = ProductDto.builder().name("상품2").price(2000).amount(10).build();

        Product org = Product.builder().id(1L).name("상품1").price(1000).amount(99).build();

        when(productRepository.save(any(Product.class))).thenReturn(org);
        when(productRepository.update(any(), any())).thenThrow(ArrayIndexOutOfBoundsException.class);

        productService.addProduct(product1);

        // when, then
        assertThatThrownBy(() -> {
            productService.modifyProduct(999L, product2);
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
}
