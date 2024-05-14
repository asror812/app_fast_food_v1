package com.example.app_fast_food.order;


import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import com.example.app_fast_food.user.entity.User;
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
    @PostMapping
    public CommonResponse<OrderResponseDTO> makeOrder(@RequestBody OrderCreateDTO createDTO) {
        return orderService.create(createDTO);
    }

    @GetMapping("{id}")
    public CommonResponse<OrderResponseDTO> getById(@PathVariable String id) {
        return orderService.getById(Long.valueOf(id));
    }

    @GetMapping
    public CommonResponse<List<OrderResponseDTO>> getAll() {
        return orderService.getAll();
    }

    /*@GetMapping("/menu/{categoryName}")
    public CommonResponse<List<OrderResponseDTO>> getAllByCategory(@PathVariable String categoryName) {
        return orderService.getByCategory(categoryName);
    }*/

    @PostMapping("/basket/{id}")
    public CommonResponse<String> addBasket(@AuthenticationPrincipal User user , @PathVariable UUID id) {
        return orderService.addToBasket(user.getId() , id);
    }


}
