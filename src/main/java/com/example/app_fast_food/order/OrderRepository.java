package com.example.app_fast_food.order;

import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order , Long> {
}
