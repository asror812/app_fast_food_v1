package com.example.app_fast_food.order;


import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.order.dto.OrderCreateDTO;
import com.example.app_fast_food.order.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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


}
