package com.jaeshim.product.management.service.impl;

import com.jaeshim.product.management.entity.Product;
import com.jaeshim.product.management.dto.ProductDto;
import com.jaeshim.product.management.repository.ProductRepository;
import com.jaeshim.product.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        Product savedProduct = productRepository.save(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
