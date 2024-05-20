package com.example.app_fast_food.product;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
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


    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO responseDTO = mapper.map(product, ProductResponseDTO.class);

        Double finalPrice = product.getPrice();

        if (!product.getDiscounts().isEmpty()) {
            for (Discount discount : product.getActiveDiscounts()) {
                finalPrice -= discount.getPercentage() * product.getPrice();
            }
        }

        responseDTO.setFinalPrice(finalPrice);
        return responseDTO;
    }

    @Override
    public void toEntity(ProductUpdateDTO productUpdateDTO, Product product) {
        mapper.map(productUpdateDTO , product);
    }
}
