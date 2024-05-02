package com.example.app_fast_food.product;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public CommonResponse<ProductResponseDTO> addProduct(@RequestBody ProductCreateDTO createDTO) {
        return productService.create(createDTO);
    }

    @GetMapping("{id}")
    public CommonResponse<ProductResponseDTO> getById(@PathVariable UUID id) {
        return productService.getById(id);
    }

    @GetMapping
    public CommonResponse<List<ProductResponseDTO>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/menu/{categoryName}")
    public CommonResponse<List<ProductResponseDTO>> getAllByCategory(@PathVariable String categoryName) {
          return productService.getByCategory(categoryName);
    }

    @

    @



}
