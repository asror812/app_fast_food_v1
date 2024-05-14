package com.example.app_fast_food.order.orderItem;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemUpdateDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class OrderItemService extends GenericService<OrderItem , Long , OrderItemResponseDTO , OrderItemCreateDTO , OrderItemUpdateDTO> {

    private final OrderItemRepository repository;
    private final Class<OrderItem> entityClass = OrderItem.class;
    private  final  OrderItemMapper mapper;

    @Override
    protected CommonResponse<OrderItemResponseDTO> internalCreate(OrderItemCreateDTO orderItemCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<OrderItemResponseDTO> internalUpdate(Long aLong, OrderItemUpdateDTO orderItemUpdateDTO) {
        return null;
    }
}
