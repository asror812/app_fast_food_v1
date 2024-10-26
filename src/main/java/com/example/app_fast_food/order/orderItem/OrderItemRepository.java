package com.example.app_fast_food.order.orderItem;

import com.example.app_fast_food.common.repository.GenericRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends GenericRepository<OrderItem , UUID> {


    void deleteAllByOrderId(UUID orderId);
}
