package com.jaeshim.product.management.dto;

import com.jaeshim.product.management.entity.domain.Product;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductDto {
    @Setter
    private Long id;

    @NotNull (message = "NULL 이어서는 안됩니다")
    private String name;

    @NotNull (message = "NULL 이어서는 안됩니다")
    private Integer price;

    @NotNull (message = "NULL 이어서는 안됩니다")
    private Integer amount;

    public static Product convertToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .amount(productDto.getAmount())
                .build();
    }

    public static ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .amount(product.getAmount())
                .build();
    }
}
