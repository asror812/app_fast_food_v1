package com.example.app_fast_food.discount;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.dto.DiscountCreateDTO;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.discount.dto.DiscountUpdateDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class DiscountService extends GenericService<Discount , UUID  , DiscountResponseDTO, DiscountCreateDTO, DiscountUpdateDTO> {

    private final DiscountRepository repository;
    private final Class<Discount> entityClass = Discount.class;
    private final DiscountMapper mapper;

    @Override
    protected GenericMapper<Discount, DiscountCreateDTO, DiscountResponseDTO, DiscountUpdateDTO> getMapper() {
        return null;
    }

    @Override
    protected CommonResponse<DiscountResponseDTO> internalCreate(DiscountCreateDTO discountCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<DiscountResponseDTO> internalUpdate(UUID uuid, DiscountUpdateDTO discountUpdateDTO) {
        return null;
    }
}
