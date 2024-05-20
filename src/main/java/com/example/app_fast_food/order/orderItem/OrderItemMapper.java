package com.example.app_fast_food.order.orderItem;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderItemMapper extends GenericMapper<OrderItem, OrderItemCreateDTO, OrderItemResponseDTO, OrderItemUpdateDTO> {

    private final ModelMapper mapper;
     @Override
    public OrderItem toEntity(OrderItemCreateDTO orderItemCreateDTO) {
        return mapper.map(orderItemCreateDTO, OrderItem.class);
    }

    @Override
    public OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
        return mapper.map(orderItem , OrderItemResponseDTO.class);
    }

    @Override
    public void toEntity(OrderItemUpdateDTO orderItemUpdateDTO, OrderItem orderItem) {
         mapper.map(orderItemUpdateDTO, orderItem);
    }
}
