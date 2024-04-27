package com.example.app_fast_food.order;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDTOMapper extends GenericMapper<Order , OrderCreateDTO, OrderResponseDTO , OrderUpdateDTO> {
    private final ModelMapper mapper;

    @Override
    public Order toEntity(OrderCreateDTO orderCreateDTO) {
        return mapper.map(orderCreateDTO, Order.class);
    }

    @Override
    public OrderResponseDTO toResponseDTO(Order order) {
        return mapper.map(order , OrderResponseDTO.class);
    }

    @Override
    public void toEntity(OrderUpdateDTO orderUpdateDTO, Order order) {
        mapper.map(orderUpdateDTO, order);
    }
}
