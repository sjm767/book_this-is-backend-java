package com.jaeshim.product.management.startup;

import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupTask implements InitializingBean {

    private final ProductService productService;

    @Override
    public void afterPropertiesSet() throws Exception {
        productService.addProduct(ProductDto.builder()
                .name("연필")
                .price(100)
                .amount(2)
                .build());

        productService.addProduct(ProductDto.builder()
                .name("필통")
                .price(1000)
                .amount(1)
                .build());
    }
}
