package com.example.app_fast_food.order;


import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.check.CheckRepository;
import com.example.app_fast_food.check.CheckResponseDTO;
import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.filial.FilialRepository;
import com.example.app_fast_food.filial.FilialService;
import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderPurchaseDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.dto.OrderUpdateDTO;
import com.example.app_fast_food.order.orderItem.OrderItem;
import com.example.app_fast_food.order.orderItem.OrderItemRepository;
import com.example.app_fast_food.order.orderItem.OrderItemService;
import com.example.app_fast_food.order.orderItem.dto.OrderItemRequestDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.ProductDTOMapper;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
    private final BonusRepository bonusRepository;
    private final ProductDTOMapper productDTOMapper;
    private final FilialRepository filialRepository;
    private final FilialService filialService;
    private final CheckRepository checkRepository;


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

        Product product = productRepository.findProductById(dto.getProductId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Product" , dto.getProductId().toString()));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RestException.EntityNotFoundException("User" , id.toString()));

       Order order = calculateProductDiscount(product);

        OrderItem orderItem = new OrderItem(null , 1  ,  product,  order);


        order.getOrderItems().add(orderItem);
        order.setUser(user);

        repository.save(order);



        List<OrderItemResponseDTO> responseDTOS = orderItemService
                .getResponseDTOS(order.getOrderItems());
        return CommonResponse.succeed(responseDTOS);
    }

    private Order calculateProductDiscount(Product product) {

        Long price = product.getPrice();
        long discount = 0L;

        for (Discount activeDiscount : product.getActiveDiscounts()) {
            if(activeDiscount.getRequiredQuantity() <= 1){
                discount += product.getPrice() * activeDiscount.getPercentage() / 100;
            }
        }
        long orderPrice = price - discount;

        return new Order(null , Collections.emptyList(),  null , null ,
                null ,  9000L , orderPrice , orderPrice  + 9000  );
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


    public CommonResponse<String> deleteBasketByUserId(UUID id) {
        repository.deleteOrderByUserIdAndOrderStatus(id , OrderStatus.BASKET);
        return CommonResponse.succeed("OK");
    }


    public CommonResponse<String> removeProduct(OrderItemRequestDTO dto) {

        Order order = repository.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Order" , dto.getOrderId().toString()));

        int deleteCount = orderItemRepository.deleteAllByOrderId(order.getId());

        if(deleteCount  == 0) {
            throw new RestException.EntityNotFoundException("Product in Order" , dto.getOrderItemId().toString());
        }

        return CommonResponse.succeed("OK");
    }


    public CommonResponse<OrderResponseDTO> updateQuantity(UUID orderId, UUID orderItemId, Integer newQuantity) {
        Order order = repository.findOrderById(orderId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket"  , orderId.toString()));

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(o -> o.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(
                        () -> new RestException.EntityNotFoundException("OrderItem" , orderItemId.toString()));

        if(newQuantity < 1) {
            throw new RestException.InvalidOperationException("Order item quantity cannot be less 1 ");
        }

        orderItem.setQuantity(newQuantity);
        orderItemRepository.save(orderItem);

        Order updateOrder = calculateOrderDiscounts(order);
        repository.save(updateOrder);


        return CommonResponse.succeed(mapper.toResponseDTO(updateOrder));
    }

    private Order calculateOrderDiscounts(Order order) {
        long orderPrice = 0L;
        long discount = 0L;

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            Discount mostPreferableDiscount = null;

            Set<Discount> activeDiscountsWithRequirements = product
                    .getActiveDiscounts().stream()
                    .filter(p -> p.getRequiredQuantity() > 1)
                    .collect(Collectors.toSet());

            Set<Discount> activeDiscountWithNoRequirements = product
                    .getActiveDiscounts()
                    .stream().filter(p -> p.getRequiredQuantity() <= 1)
                    .collect(Collectors.toSet());

            //Discount without requirements
            for (Discount activeDiscounts : activeDiscountWithNoRequirements) {
                discount += (product.getPrice() * activeDiscounts.getPercentage() / 100) *
                        activeDiscounts.getRequiredQuantity();
            }

            //Discount with requirements : Find the most preferable discount
            //Where the discount required quantity is the closest one to item quantity
            for (Discount activeDiscount : activeDiscountsWithRequirements) {

                if (item.getQuantity() >= activeDiscount.getRequiredQuantity() &&
                        (mostPreferableDiscount == null || activeDiscount.getRequiredQuantity() > mostPreferableDiscount.getPercentage())) {
                    mostPreferableDiscount = activeDiscount;
                }
            }

            //Check if there is discount with requirements available
            if (mostPreferableDiscount != null) {
                int productDiscount = item.getQuantity() / mostPreferableDiscount.getRequiredQuantity();

                discount += (productDiscount * product.getPrice() / 100) * item.getQuantity();
            }

            orderPrice += product.getPrice() * item.getQuantity();

        }

        order.setShippingCost(9000L);
        order.setOrderPrice(orderPrice - discount);
        order.setTotalPrice(orderPrice - discount + order.getShippingCost());

        return order;
    }

    public CommonResponse<ProductResponseDTO> confirmBonus(UUID selectedProduct) {

        Product product = productRepository.findProductById(selectedProduct)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Product" , selectedProduct.toString()));

        return CommonResponse
                .succeed(productDTOMapper.toResponseDTO(product));
    }

    public CommonResponse<String> confirmOrder(UUID orderId , UUID userId) {

        Order order = repository
                .findBasketByUserId(orderId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket" , orderId.toString()));

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("User" , userId.toString()));

        order.setOrderStatus(OrderStatus.PAYED);
        repository.save(order);

        Filial nearestOne = filialService.findTheNearestOne(user);

        Check check = new Check(null , user , nearestOne , order.getTotalPrice() , "John" );
        checkRepository.save(check);

        return CommonResponse.succeed("OK");
    }
}
