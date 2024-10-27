package com.jaeshim.product.management.dto;

import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
