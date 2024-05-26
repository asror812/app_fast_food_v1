package com.example.app_fast_food.filial;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.filial.dto.FilialCreateDTO;
import com.example.app_fast_food.filial.dto.FilialResponseDTO;
import com.example.app_fast_food.filial.dto.FilialUpdateDTO;
import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class FilialService extends GenericService<Filial , UUID , FilialResponseDTO, FilialCreateDTO , FilialUpdateDTO> {

    private final FilialRepository repository;
    private final Class<Filial>  entityClass = Filial.class;
    private final FilialDTOMapper mapper ;



    @Override
    protected GenericMapper<Filial, FilialCreateDTO, FilialResponseDTO, FilialUpdateDTO> getMapper() {
        return null;
    }

    @Override
    protected CommonResponse<FilialResponseDTO> internalCreate(FilialCreateDTO filialCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<FilialResponseDTO> internalUpdate(UUID uuid, FilialUpdateDTO filialUpdateDTO) {
        return null;
    }

    public Filial findTheNearestOne(User user) {

        List<Filial> all = repository.findAll();

        return null;
    }
}
