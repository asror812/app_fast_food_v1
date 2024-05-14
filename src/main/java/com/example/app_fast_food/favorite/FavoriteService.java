package com.example.app_fast_food.favorite;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.ProductRepository;
import com.example.app_fast_food.product.ProductService;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.UserRepository;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FavoriteService  {

    private final UserRepository userRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;


    public CommonResponse<List<ProductResponseDTO>> getFavorites(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        Set<Product> favoriteProducts = user.getFavoriteProducts();

        return CommonResponse.succeed(productService.getProductResponseDTOS(favoriteProducts.stream().toList())) ;
    }

    public CommonResponse<String> addFavorite(UUID userId, UUID productId ) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        Product product =  productRepository.findProductById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product with id : %s not found".formatted(productId)));

         user.favoriteProducts.add(product);

         userRepository.save(user);

         return CommonResponse.succeed("OK");
    }
}
