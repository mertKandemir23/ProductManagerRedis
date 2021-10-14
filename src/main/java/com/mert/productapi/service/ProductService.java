package com.mert.productapi.service;

import com.google.common.collect.Lists;
import com.mert.productapi.dto.ProductDto;
import com.mert.productapi.dto.ProductRequest;
import com.mert.productapi.exception.ProductNotFoundException;
import com.mert.productapi.model.Product;
import com.mert.productapi.repository.ProductRedisRepository;
import com.mert.productapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRedisRepository productRepository;
    private final static String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";


  @Cacheable(value = "product")
    public List<ProductDto> getAllProducts() {

        List<Product> productList = Lists.newArrayList(productRepository.findAll());
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            productDtoList.add(modelMapper.map(product, ProductDto.class));
        }
        return productDtoList;


    }

    public ProductDto getProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            Optional<Product> productOptional = productRepository.findById(id);
            return modelMapper.map(productOptional.get(), ProductDto.class);
        } else {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
    }

    @CachePut(value = "product")
    public ProductDto createProduct(ProductRequest productRequest) {
        Product product1 = modelMapper.map(productRequest, Product.class);
        product1.setUpdateAt(LocalDateTime.now());
        product1.setCreatedAt(LocalDateTime.now());
        productRepository.save(product1);
        return modelMapper.map(product1, ProductDto.class);
    }

    @CacheEvict(value = "product", allEntries = true)
    public ProductDto updateProduct(Long id, ProductRequest productRequest) {
        Product product1 = productRepository.findById(id).orElse(null);
        if (product1 == null) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        } else {

            product1.setProductName(productRequest.getProductName());
            product1.setProductDescription(productRequest.getProductDescription());
            product1.setPrice(productRequest.getPrice());
            product1.setUpdateAt(LocalDateTime.now());
            productRepository.save(product1);
            return modelMapper.map(product1, ProductDto.class);


        }

    }
    @CacheEvict(value = "product", allEntries = true)
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @CacheEvict(value = "product", allEntries = true)
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }


}

