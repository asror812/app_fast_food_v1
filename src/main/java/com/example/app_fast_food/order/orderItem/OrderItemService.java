package com.example.app_fast_food.order.orderItem;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemUpdateDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@Getter
@RequiredArgsConstructor
public class OrderItemService extends GenericService<OrderItem , UUID, OrderItemResponseDTO , OrderItemCreateDTO, OrderItemUpdateDTO> {

    private final OrderItemRepository repository;
    private final Class<OrderItem> entityClass = OrderItem.class;
    private  final  OrderItemMapper mapper;

    @Override
    protected CommonResponse<OrderItemResponseDTO> internalCreate(OrderItemCreateDTO orderItemCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<OrderItemResponseDTO> internalUpdate(UUID aLong, OrderItemUpdateDTO orderItemUpdateDTO) {
        return null;
    }

    public List<OrderItemResponseDTO> getResponseDTOS(List<OrderItem> orderItems) {
        return orderItems.stream().map(mapper::toResponseDTO)
                .toList();
    }


}
