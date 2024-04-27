package com.example.app_fast_food.product;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductDTOMapper extends GenericMapper<Product, ProductCreateDTO , ProductResponseDTO , ProductUpdateDTO> {

   private final ModelMapper mapper;

    @Override
    public Product toEntity(ProductCreateDTO productCreateDTO) {
        return  mapper.map(productCreateDTO , Product.class);
    }


    //Todo
    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        return mapper.map(product , ProductResponseDTO.class);
    }

    @Override
    public void toEntity(ProductUpdateDTO productUpdateDTO, Product product) {
        mapper.map(productUpdateDTO , product);
    }
}
