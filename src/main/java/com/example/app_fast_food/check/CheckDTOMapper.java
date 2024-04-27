package com.example.app_fast_food.check;


import com.example.app_fast_food.check.dto.CheckCreateDTO;
import com.example.app_fast_food.check.dto.CheckUpdateDTO;
import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.common.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckDTOMapper extends GenericMapper<Check, CheckCreateDTO , CheckResponseDTO , CheckUpdateDTO> {

    private final ModelMapper mapper;

    @Override
    public Check toEntity(CheckCreateDTO checkCreateDTO) {
        return mapper.map(checkCreateDTO, Check.class);
    }

    @Override
    public CheckResponseDTO toResponseDTO(Check check) {
        return mapper.map(check, CheckResponseDTO.class);
    }

    @Override
    public void toEntity(CheckUpdateDTO checkUpdateDTO, Check check) {
       mapper.map(checkUpdateDTO, check);
    }
}
