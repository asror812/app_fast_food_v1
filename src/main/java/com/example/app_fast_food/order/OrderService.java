package com.example.app_fast_food.order;


import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Getter
public class OrderService extends GenericService<Order, Long , OrderResponseDTO, OrderCreateDTO, OrderUpdateDTO> {

    private final OrderRepository repository;
    private final Class<Order> entityClass = Order.class;
    private final OrderDTOMapper mapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


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

    public CommonResponse<String> addToBasket(UUID user_id, UUID product_id) {

        Optional<Order> order = orderRepository.findBasketByUserId(user_id);

        if (order.isPresent()) {
            Product product = productRepository.findById(product_id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem =
                    new OrderItem(null, 1, product, order.get());

            return CommonResponse.succeed("OK");
        }




    }


}
