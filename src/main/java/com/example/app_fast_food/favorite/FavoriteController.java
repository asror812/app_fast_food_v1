package com.example.app_fast_food.favorite;


import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;


    @GetMapping
    public CommonResponse<List<ProductResponseDTO>> getFavoriteProducts(@AuthenticationPrincipal User user) {
        return favoriteService.getFavorites(user.getId());
    }


    @PostMapping("/{product_id}")
    public CommonResponse<String> addFavorite(@AuthenticationPrincipal User user, @PathVariable UUID product_id){
        return favoriteService.addFavorite(user.getId() , product_id);
    }

 }
