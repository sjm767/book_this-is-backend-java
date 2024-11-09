package com.jaeshim.product.management.service.impl;

import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.entity.domain.Product;
import com.jaeshim.product.management.repository.ProductRepository;
import com.jaeshim.product.management.service.ProductService;
import com.jaeshim.product.management.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository listProductRepository;
    private final ValidationService validationService;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = ProductDto.convertToProduct(productDto);
        validationService.checkValid(product);

        Product savedProduct = listProductRepository.save(product);
        return ProductDto.convertToDto(savedProduct);
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = listProductRepository.findById(id);
        return ProductDto.convertToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = listProductRepository.findAll();
        return products.stream()
                .map(ProductDto::convertToDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductByNameContains(String productName) {
        List<Product> products = listProductRepository.findByNameContains(productName);
        return products.stream()
                .map(ProductDto::convertToDto)
                .toList();
    }

    @Override
    public ProductDto modifyProduct(Long id, ProductDto productDto) {
        Product product = ProductDto.convertToProduct(productDto);
        product.setId(id);
        listProductRepository.update(id, product);

        return ProductDto.convertToDto(product);
    }

    @Override
    public void remove(Long id) {
        listProductRepository.deleteById(id);
    }
}
