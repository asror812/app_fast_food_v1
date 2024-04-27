package com.example.app_fast_food.product;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.product.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product , UUID , ProductResponseDTO , ProductCreateDTO , ProductUpdateDTO > {

    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final ProductDTOMapper mapper;

    @Override
    protected ProductResponseDTO internalCreate(ProductCreateDTO productCreateDTO) {
        return null;
    }

    @Override
    protected ProductResponseDTO internalUpdate(UUID uuid, ProductUpdateDTO productUpdateDTO) {
        return null;
    }
}
