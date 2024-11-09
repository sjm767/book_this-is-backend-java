package com.jaeshim.product.management.controller;

import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    public List<ProductDto> getProducts(@RequestParam(required = false) String name) {
        if (name == null) {
            return productService.getAllProducts();
        }

        return productService.getProductByNameContains(name);
    }

    @GetMapping(value = "/products/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping(value = "/product")
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PutMapping(value = "/product/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.modifyProduct(id, productDto);
    }

    @DeleteMapping(value = "product/{id}")
    public void removeProduct(@PathVariable Long id) {
        productService.remove(id);
    }
}
