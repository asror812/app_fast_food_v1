package com.example.app_fast_food.order;


import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.order.orderItem.OrderItemRepository;
import com.example.app_fast_food.order.orderItem.OrderItemService;
import com.example.app_fast_food.order.orderItem.dto.OrderItemRequestDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;


    @Override
    protected CommonResponse<OrderResponseDTO> internalCreate(OrderCreateDTO dto) {
      return  null;
    }

    @Override
    protected CommonResponse<OrderResponseDTO> internalUpdate(Long aLong, OrderUpdateDTO orderUpdateDTO) {
        return null;
    }


    public CommonResponse<List<OrderResponseDTO>> getByOrderStatus(String status) {
        List<Order> orders = repository.findByOrderStatus(OrderStatus.valueOf(status));

        return CommonResponse
                .succeed(orders.stream()
                .map(mapper::toResponseDTO)
                .toList());
    }

    public CommonResponse<List<OrderItemResponseDTO>> addProduct(UUID id, UUID productId) {

        Product product = productRepository
                .findById(id).orElseThrow(() ->
                        new RestException.EntityNotFoundException("Product" , productId.toString()) );

        User user = userRepository
                .findById(id).orElseThrow(() ->
                        new RestException.EntityNotFoundException("User" , id.toString()));

        Order order = repository
                .findBasketByUserId(id).orElseThrow(() ->
                        new RestException.EntityNotFoundException("Basket"  , " of user : " + user.getId()));


        OrderItem orderItem = new OrderItem(null , 1 , product , order);
        orderItemRepository.save(orderItem);

        order.getOrderItems().add(orderItem);

        repository.save(order);

        List<OrderItemResponseDTO> dtos = orderItemService
                .getResponseDTOS(order.getOrderItems());

        return CommonResponse.succeed(dtos);
    }

    public CommonResponse<List<OrderItemResponseDTO>> createBasket(UUID id, OrderItemCreateDTO dto) {

        orderRepository.deleteOrderByUserIdAndOrderStatus(id , OrderStatus.BASKET);

        Optional<Product> product = productRepository.findProductById(dto.getProductId());
        Optional<User> user = userRepository.findById(id);
        if(product.isEmpty()) {
            throw new RestException.EntityNotFoundException("Product" , dto.getProductId().toString());
        }

        if(user.isEmpty()) {
            throw new RestException.EntityNotFoundException("User" , id.toString());
        }
        OrderItem orderItem = new OrderItem(null , 1  ,  product.get(),  null);

        Order order = new Order(null , List.of(orderItem) , OrderStatus.BASKET ,  null , user.get());


        orderItem.setOrder(order);

        orderItemRepository.save(orderItem);
        repository.save(order);



        List<OrderItemResponseDTO> responseDTOS = orderItemService
                .getResponseDTOS(order.getOrderItems());
        return CommonResponse.succeed(responseDTOS);
    }

    public CommonResponse<OrderResponseDTO> getBasketOrderItems(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow( () ->
                        new RestException.EntityNotFoundException("User" , id.toString()));

        Order basket = repository.findBasketByUserId(user.getId())
                .orElseThrow(() ->
                        new RestException.EntityNotFoundException("Basket"  , "of user "  + id));

        return CommonResponse.succeed(mapper.toResponseDTO(basket)) ;
    }

    private List<OrderResponseDTO> getResponseDTOS(List<Order> order) {
        List<OrderResponseDTO> dtos = new ArrayList<>();

        for (Order or : order) {
            dtos.add(mapper.toResponseDTO(or));
        }
        return dtos;
    }

    public CommonResponse<String> deleteBasketByUserId(UUID id) {
        repository.deleteOrderByUserIdAndOrderStatus(id , OrderStatus.BASKET);
        return CommonResponse.succeed("OK");
    }


    public CommonResponse<String> removeProduct(UUID id, OrderItemRequestDTO dto) {

        Order order = repository.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Order" , dto.getOrderId().toString()));

        int deleteCount = orderItemRepository.deleteAllByOrderId(order.getId());

        if(deleteCount  == 0) {
            throw new RestException.EntityNotFoundException("Product in Order" , dto.getOrderItemId().toString());
        }

        return CommonResponse.succeed("OK");
    }

    public CommonResponse<OrderResponseDTO> increment(UUID id, OrderItemRequestDTO dto) {

        Order order = repository.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket"  , id.toString()));

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(o -> o.getId().equals(dto.getOrderItemId()))
                .findFirst()
                .orElseThrow(
                        () -> new RestException.EntityNotFoundException("OrderItem" , dto.getOrderItemId().toString()));

        orderItem.setQuantity(orderItem.getQuantity() + 1);
        orderItemRepository.save(orderItem);



        return CommonResponse.succeed(getResponseDTOS(List.of(order)).get(0));
    }

    public CommonResponse<OrderResponseDTO> decrement(UUID id, OrderItemRequestDTO dto) {

        Order order = repository.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket"  , id.toString()));

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(o -> o.getId().equals(dto.getOrderItemId()))
                .findFirst()
                .orElseThrow(
                        () -> new RestException.EntityNotFoundException("OrderItem" , dto.getOrderItemId().toString()));


        if(orderItem.getQuantity() <= 1) {
              throw new RestException.InvalidOperationException("Order item quantity cannot be less 1 ");
        }

        orderItem.setQuantity(orderItem.getQuantity() - 1);
        orderItemRepository.save(orderItem);

        return CommonResponse.succeed(getResponseDTOS(List.of(order)).get(0));

    }
}
