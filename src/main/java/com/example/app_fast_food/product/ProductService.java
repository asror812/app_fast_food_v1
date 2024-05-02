package com.example.app_fast_food.product;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.product.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product , UUID , ProductResponseDTO , ProductCreateDTO , ProductUpdateDTO > {

    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final ProductDTOMapper mapper;

    @Override
    protected CommonResponse<ProductResponseDTO> internalCreate(ProductCreateDTO productCreateDTO) {

        Product entity = mapper.toEntity(productCreateDTO);
        repository.save(entity);
        return CommonResponse.succeed(mapper.toResponseDTO(entity));
    }

    @Override
    protected CommonResponse<ProductResponseDTO> internalUpdate(UUID uuid, ProductUpdateDTO productUpdateDTO) {
        return null;
    }

    public CommonResponse<List<ProductResponseDTO>> getByCategory(String categoryName) {
        List<Product> products = repository.findProductsByCategoryName(categoryName);

        return CommonResponse.succeed(products.stream().map(mapper::toResponseDTO).toList());
    }
}
