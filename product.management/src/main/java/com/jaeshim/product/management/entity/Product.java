package com.jaeshim.product.management.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Product {
    @Setter
    private long id;
    private String name;
    private Integer price;
    private Integer amount;
}
