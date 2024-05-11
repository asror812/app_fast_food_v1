package com.example.app_fast_food.bonus;

import com.example.app_fast_food.bonus.dto.BonusCreateDTO;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.bonus.dto.BonusUpdateDTO;
import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Getter
@RequiredArgsConstructor
public class BonusService extends GenericService<Bonus , Long , BonusResponseDTO , BonusCreateDTO , BonusUpdateDTO> {

    private final BonusRepository repository;
    private final Class<Bonus> entityClass = Bonus.class;
    private final BonusDTOMapper mapper;

    @Override
    protected CommonResponse<BonusResponseDTO> internalCreate(BonusCreateDTO bonusCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<BonusResponseDTO> internalUpdate(Long aLong, BonusUpdateDTO bonusUpdateDTO) {
        return null;
    }
}
