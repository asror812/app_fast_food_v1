package com.example.app_fast_food.check;

import com.example.app_fast_food.check.dto.CheckCreateDTO;
import com.example.app_fast_food.check.dto.CheckUpdateDTO;
import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class CheckService extends GenericService<Check , UUID , CheckResponseDTO , CheckCreateDTO , CheckUpdateDTO> {


    private final Class<Check> entityClass = Check.class;
    private final CheckRepository repository;
    private final CheckDTOMapper mapper;


    //Todo
    @Override
    protected CommonResponse<CheckResponseDTO> internalCreate(CheckCreateDTO checkCreateDTO) {
        return null;
    }

    //Todo
    @Override
    protected CommonResponse<CheckResponseDTO> internalUpdate(UUID uuid, CheckUpdateDTO checkUpdateDTO) {
        return null;
    }
}
