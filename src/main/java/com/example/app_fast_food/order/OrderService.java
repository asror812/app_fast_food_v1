package com.example.app_fast_food.order;


import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.bonus.BonusDTOMapper;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.bonus.BonusService;
import com.example.app_fast_food.check.CheckRepository;
import com.example.app_fast_food.check.dto.CheckCreateDTO;
import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.DiscountService;
import com.example.app_fast_food.filial.FilialRepository;
import com.example.app_fast_food.filial.FilialService;
import com.example.app_fast_food.filial.NearestFilial;
import com.example.app_fast_food.filial.entity.Filial;
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
import com.example.app_fast_food.product.ProductDTOMapper;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import java.util.*;


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
    private final BonusRepository bonusRepository;
    private final ProductDTOMapper productDTOMapper;
    private final FilialRepository filialRepository;
    private final FilialService filialService;
    private final CheckRepository checkRepository;
    private final BonusDTOMapper bonusDTOMapper;
    private final ModelMapper modelMapper;
    private final BonusService bonusService;
    private final DiscountService discountService;


    @Override
    protected CommonResponse<OrderResponseDTO> internalCreate(OrderCreateDTO dto) {
        return null;
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
                        new RestException.EntityNotFoundException("Product", productId.toString()));

        User user = userRepository
                .findById(id).orElseThrow(() ->
                        new RestException.EntityNotFoundException("User", id.toString()));

        Order order = repository
                .findBasketByUserId(id).orElseThrow(() ->
                        new RestException.EntityNotFoundException("Basket", " of user : " + user.getId()));


        OrderItem orderItem = new OrderItem(null, 1, product, order);
        orderItemRepository.save(orderItem);

        order.getOrderItems().add(orderItem);
        repository.save(order);

        List<OrderItemResponseDTO> dtos = orderItemService
                .getResponseDTOS(order.getOrderItems());

        return CommonResponse.succeed(dtos);
    }

    public CommonResponse<List<OrderItemResponseDTO>> createBasket(UUID id, OrderItemCreateDTO dto) {

        repository.deleteOrderByUserIdAndOrderStatus(id, OrderStatus.BASKET);

        Product product = productRepository.findProductById(dto.getProductId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Product", dto.getProductId().toString()));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RestException.EntityNotFoundException("User", id.toString()));

        Order order = new Order(
                null, Collections.emptyList(), OrderStatus.BASKET,
                PaymentType.CASH, user  , Collections.emptySet() , Collections.emptySet());

        OrderItem orderItem = new OrderItem(null, 1, product, order);


        order.getOrderItems().add(orderItem);
        order.setUser(user);

        repository.save(order);

        List<OrderItemResponseDTO> responseDTOS = orderItemService
                .getResponseDTOS(order.getOrderItems());

        return CommonResponse.succeed(responseDTOS);
    }

    public CommonResponse<OrderResponseDTO> getBasket(User user) {

        Order order = repository.findBasketByUserId(user.getId())
                .orElseThrow(() ->
                        new RestException.EntityNotFoundException("Basket", "of user " + user.getId()));

        OrderResponseDTO basket = calculateOrderDiscountsAndBonus(order , user);

        return CommonResponse.succeed(basket);
    }


    public CommonResponse<String> deleteUserBasket(UUID id) {
        repository.deleteOrderByUserIdAndOrderStatus(id, OrderStatus.BASKET);
        return CommonResponse.succeed("OK");
    }


    //todo
    public CommonResponse<String> removeProducts(@AuthenticationPrincipal User user , UUID orderId) {

        Order order = repository.findOrderById(orderId)
                .orElseThrow(() ->
                        new RestException.EntityNotFoundException("Order", orderId.toString()));

       // orderItemRepository.deleteAllByOrderId(order.getId());

        order.setOrderItems(Collections.emptyList());
        order.setBonuses(Collections.emptySet());
        order.setDiscounts(Collections.emptySet());

        repository.save(order);


        return CommonResponse.succeed("OK");
    }


/*    public CommonResponse<OrderResponseDTO> updateQuantity(UUID orderId, UUID orderItemId, Integer newQuantity) {

        if (newQuantity < 1) {
            throw new RestException.InvalidOperationException("Order item quantity cannot be less 1 ");
        }

        Order order = repository.findOrderById(orderId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket", orderId.toString()));

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(o -> o.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(
                        () -> new RestException.EntityNotFoundException("OrderItem", orderItemId.toString()));


        orderItem.setQuantity(newQuantity);
        orderItemRepository.save(orderItem);

        Order basket = calculateOrderDiscountsAndBonus(order , user);
        repository.save(order);

        return CommonResponse.succeed(basket);
    }*/

    private OrderResponseDTO calculateOrderDiscountsAndBonus(Order order , @AuthenticationPrincipal User user) {

        Set<Bonus> orderBonuses = bonusService.getOrderBonuses(user , order);

        Set<Discount> orderDiscounts = discountService.getOrderDiscounts(order);

        order.setDiscounts(orderDiscounts);
        order.setBonuses(orderBonuses);

        repository.save(order);

        return mapper.toResponseDTO(order);
    }

    /*
    public CommonResponse<ProductResponseDTO> confirmBonus(UUID selectedBonus) {

        Bonus bonus = bonusRepository.findById(selectedBonus)
                .orElseThrow(() ->
                        new RestException.EntityNotFoundException("Product" , selectedBonus.toString()));

        return CommonResponse
                .succeed(bonusDTOMapper.toResponseDTO(bonus));
    }*/

    public CommonResponse<String> confirmOrder(CheckCreateDTO dto , User user) {

        Order order = repository
                .findBasketByUserId(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket" , dto.getOrderId().toString()));

        order.setOrderStatus(OrderStatus.IN_PROCESS);
        repository.save(order);

        NearestFilial nearestOne = filialService.findTheNearestOne(dto.getLongitude() , dto.getLatitude());
        if(nearestOne.getDistance() >= 5){
            throw new RestException.TooFarException("We cannot deliver to your location");
        }

        Check check = new Check(null , order , user ,  dto.getTotalAmount(), dto.getTotalDiscount() ,
                dto.getTotalPrice(), nearestOne , "John" );
        checkRepository.save(check);

        return CommonResponse.succeed("OK");
    }

    public CommonResponse<String> removeProduct(User user, OrderItemRequestDTO dto) {
        Order order = repository
                .findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket" , dto.getOrderId().toString()));

        OrderItem orderItem = orderItemRepository
                .findById(dto.getOrderItemId())
                .orElseThrow(() -> new RestException.EntityNotFoundException("Order item" , dto.getOrderItemId().toString()));

        order.getOrderItems().remove(orderItem);
        repository.save(order);

        return CommonResponse.succeed("OK");
    }

    public CommonResponse<ProductResponseDTO> selectBonus(UUID orderId, UUID bonusId) {
        Order order = repository
                .findOrderById(orderId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Basket" , orderId.toString()));

        Product p = productRepository.findProductById(bonusId)
                .orElseThrow(() -> new RestException.EntityNotFoundException("Product" , bonusId.toString()));

        OrderItem item  = new OrderItem(null , 1, p, order);

        order.getOrderItems().add(item);
        repository.save(order);

        return CommonResponse.succeed(productDTOMapper.toResponseDTO(p));
    }

    public boolean checkOrderPrice(Order order, Long conditionValue) {
        long totalPrice = 0;
        for(OrderItem orderItem : order.getOrderItems()) {
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }

        return totalPrice >= conditionValue;
    }
}
