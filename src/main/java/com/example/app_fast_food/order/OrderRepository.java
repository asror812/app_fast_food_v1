package com.example.app_fast_food.order;

import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends GenericRepository<Order , Long> {


    @Query("SELECT o FROM Order o WHERE o.userId =: userId AND o.orderStatus = 'BASKET'")
    Optional<Order> findBasketByUserId(UUID userId);
}
