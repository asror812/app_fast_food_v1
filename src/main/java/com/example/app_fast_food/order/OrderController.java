package com.example.app_fast_food.order;


import com.example.app_fast_food.bonus.BonusService;
import com.example.app_fast_food.bonus.dto.BonusRequestDTO;
import com.example.app_fast_food.check.dto.CheckCreateDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.order.dto.OrderRequestDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemRequestDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final BonusService bonusService;

    @PostMapping("/add-to-existing-basket")
    public CommonResponse<List<OrderItemResponseDTO>> addProductToBasket(@AuthenticationPrincipal User user , @RequestBody OrderItemCreateDTO dto) {
        return orderService.addProduct(user.getId() , dto.getProductId() );
    }

    @PostMapping("/create-basket")
    public CommonResponse<List<OrderItemResponseDTO>> createItemWithBasket(@AuthenticationPrincipal User user , @RequestBody OrderItemCreateDTO dto) {
         return orderService.createBasket(user.getId() , dto);
    }


    @GetMapping("/{id}")
    public CommonResponse<OrderResponseDTO> getById(@PathVariable String id) {
        return orderService.getById(Long.valueOf(id));
    }

    @GetMapping
    public CommonResponse<List<OrderResponseDTO>> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/status/{status}")
    public CommonResponse<List<OrderResponseDTO>> getAllByOrderStatus(@PathVariable String status) {
        return orderService.getByOrderStatus(status);
    }

    @GetMapping("/basket")
    public CommonResponse<OrderResponseDTO> getBasket(@AuthenticationPrincipal User user) {
        return orderService.getBasket(user);
    }

    @PostMapping("/delete/basket")
    public CommonResponse<String> deleteBasket(@AuthenticationPrincipal User user) {
        return orderService.deleteUserBasket(user.getId());
    }

    @PostMapping("/remove-all")
    public CommonResponse<String> removeProductsFromBasket(@AuthenticationPrincipal User user , @RequestBody UUID orderId) {
        return orderService.removeProducts(user , orderId);
    }

    @PostMapping("/remove-product")
    public CommonResponse<String> removeProductFromBasket(@AuthenticationPrincipal User user , @RequestBody OrderItemRequestDTO dto) {
        return orderService.removeProduct(user , dto);
    }

   /* @PostMapping("/update-quantity")
    public CommonResponse<OrderResponseDTO> updateOrderItemQuantity(@RequestBody OrderItemRequestDTO dto) {
        return orderService.updateQuantity(dto.getOrderId() , dto.getOrderItemId() , dto.getNewQuantity() );
    }*/

    @PostMapping("/select-bonus")
    public CommonResponse<ProductResponseDTO> chooseBonus(UUID productId , UUID orderId) {
        return orderService.selectBonus(orderId , productId);
    }

    @PostMapping("/confirm-order")
    public CommonResponse<String> confirmOrder(@AuthenticationPrincipal User user , @Valid @RequestBody CheckCreateDTO createDTO) {
        return orderService.confirmOrder(createDTO, user );
    }



}
