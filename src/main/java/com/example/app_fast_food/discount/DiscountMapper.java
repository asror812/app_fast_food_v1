package com.example.app_fast_food.discount;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.discount.dto.DiscountCreateDTO;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.discount.dto.DiscountUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DiscountMapper extends GenericMapper<Discount , DiscountCreateDTO, DiscountResponseDTO, DiscountUpdateDTO> {

    private final ModelMapper mapper ;
    @Override
    public Discount toEntity(DiscountCreateDTO discountCreateDTO) {
        return mapper.map(discountCreateDTO, Discount.class);
    }

    @Override
    public DiscountResponseDTO toResponseDTO(Discount discount) {
        return mapper.map(discount, DiscountResponseDTO.class);
    }

    @Override
    public void toEntity(DiscountUpdateDTO discountUpdateDTO, Discount discount) {
               mapper.map(discountUpdateDTO, discount);
    }
}
