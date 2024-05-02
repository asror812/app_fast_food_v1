package com.example.app_fast_food.product;

import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends GenericRepository<Product , UUID> {


    List<Product> findProductsByCategoryName(String name);
}
