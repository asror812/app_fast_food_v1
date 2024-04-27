package com.example.app_fast_food.product;

import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.product.entity.Product;

import java.util.UUID;

public interface ProductRepository extends GenericRepository<Product , UUID> {
}
