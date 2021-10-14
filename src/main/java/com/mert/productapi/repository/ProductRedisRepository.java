package com.mert.productapi.repository;

import com.mert.productapi.model.Product;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
public interface ProductRedisRepository extends CrudRepository<Product, Long> {
}
