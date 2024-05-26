package com.example.app_fast_food.product;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public CommonResponse<List<ProductResponseDTO>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/menu/{categoryName}")
    public CommonResponse<List<ProductResponseDTO>> getAllByCategory(@PathVariable String categoryName) {
          return productService.getByCategory(categoryName);
    }

    @GetMapping("/main")
    public CommonResponse<List<ProductResponseDTO>> get4PopularProducts(){
        return productService.get4PopularProducts();
    }

    @GetMapping("/campaign")
    public CommonResponse<List<ProductResponseDTO>> getCampaignProducts() {
        return productService.getCampaignProducts();
    }

    @GetMapping("/product/{id}")
    public CommonResponse<ProductResponseDTO> getProductIndividual(@PathVariable UUID id) {
        return productService.getSpecificProduct(id);
    }

}
