package com.example.app_fast_food.order;


import com.example.app_fast_food.bonus.dto.BonusDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemRequestDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemCreateDTO;
import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import com.example.app_fast_food.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

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
        return orderService.getBasketOrderItems(user.getId());
    }

    @PostMapping("/delete/basket")
    public CommonResponse<String> deleteBasket(@AuthenticationPrincipal User user) {
        return orderService.deleteBasketByUserId(user.getId());
    }

    @PostMapping("/remove")
    public CommonResponse<String> removeProductFromBasket(@AuthenticationPrincipal User user, @RequestBody OrderItemRequestDTO dto) {
        return orderService.removeProduct(user.getId() , dto);
    }

    @PostMapping("/increment")
    public CommonResponse<OrderResponseDTO> incrementQuantity(@AuthenticationPrincipal User user, @RequestBody OrderItemRequestDTO dto) {
        return orderService.increment(user.getId() , dto );
    }

    @PostMapping("/decrement")
    public CommonResponse<OrderResponseDTO> decrementQuantity(@AuthenticationPrincipal User user, @RequestBody OrderItemRequestDTO dto) {
        return orderService.decrement(user.getId() , dto);
    }

    @PostMapping("/make-order")
    public CommonResponse<BonusDTO>



}
