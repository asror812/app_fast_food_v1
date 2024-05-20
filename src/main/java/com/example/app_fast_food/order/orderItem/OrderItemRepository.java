package com.example.app_fast_food.order.orderItem;

import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends GenericRepository<OrderItem , Long> {


    int deleteAllByOrderId(long orderId);
}
