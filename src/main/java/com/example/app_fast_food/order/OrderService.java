package com.example.app_fast_food.order;


import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Getter
public class OrderService extends GenericService<Order, Long , OrderResponseDTO, OrderCreateDTO, OrderUpdateDTO> {

    private final OrderRepository repository;
    private final Class<Order> entityClass = Order.class;
    private final OrderDTOMapper mapper;


    //Todo
    @Override
    protected CommonResponse<OrderResponseDTO> internalCreate(OrderCreateDTO orderCreateDTO) {


        return null;
    }

    //Todo
    @Override
    protected CommonResponse<OrderResponseDTO> internalUpdate(Long aLong, OrderUpdateDTO orderUpdateDTO) {
        return null;
    }
}
