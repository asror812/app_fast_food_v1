package com.example.app_fast_food.filial;


import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.filial.dto.FilialCreateDTO;
import com.example.app_fast_food.filial.dto.FilialResponseDTO;
import com.example.app_fast_food.filial.dto.FilialUpdateDTO;
import com.example.app_fast_food.filial.entity.Filial;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilialDTOMapper extends GenericMapper<Filial , FilialCreateDTO , FilialResponseDTO , FilialUpdateDTO> {

    private final ModelMapper mapper;
    @Override
    public Filial toEntity(FilialCreateDTO filialCreateDTO) { return mapper.map(filialCreateDTO , Filial.class); }


    //Todo
    @Override
    public FilialResponseDTO toResponseDTO(Filial filial) {return mapper.map(filial , FilialResponseDTO.class); }

    @Override
    public void toEntity(FilialUpdateDTO filialUpdateDTO, Filial filial) { mapper.map(filialUpdateDTO , filial);}
}
