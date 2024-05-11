package com.example.app_fast_food.bonus;

import com.example.app_fast_food.bonus.dto.BonusCreateDTO;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.bonus.dto.BonusUpdateDTO;
import com.example.app_fast_food.common.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BonusDTOMapper extends GenericMapper<Bonus, BonusCreateDTO, BonusResponseDTO, BonusUpdateDTO> {


    private final ModelMapper mapper;

    @Override
    public Bonus toEntity(BonusCreateDTO bonusCreateDTO) {
        return mapper.map(bonusCreateDTO, Bonus.class);
    }

    @Override
    public BonusResponseDTO toResponseDTO(Bonus bonus) {
        return mapper.map(bonus, BonusResponseDTO.class);
    }

    @Override
    public void toEntity(BonusUpdateDTO bonusProductUpdateDTO, Bonus bonus) {
      mapper.map(bonusProductUpdateDTO, bonus);
    }
}
