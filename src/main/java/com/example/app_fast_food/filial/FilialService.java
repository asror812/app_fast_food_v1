package com.example.app_fast_food.filial;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.filial.dto.FilialCreateDTO;
import com.example.app_fast_food.filial.dto.FilialResponseDTO;
import com.example.app_fast_food.filial.dto.FilialUpdateDTO;
import com.example.app_fast_food.filial.entity.Filial;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    private static final double EARTH_RADIUS = 6378.1370;




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

    public NearestFilial findTheNearestOne(Double longitude , Double latitude ) {

        List<Filial> all = repository.findAll();

        Filial nearest = null;
        double minDistance = Integer.MAX_VALUE;
        for(Filial filial : all) {
            double c1 = calculateDistance(filial.getLatitude(), filial.getLongitude(), latitude, longitude);

            if (minDistance > c1) minDistance = c1;
            nearest = filial;
        }

        return new NearestFilial(nearest , minDistance);
    }

    private double calculateDistance(Double latitude, Double longitude, Double latitude1, Double longitude1) {
        double dLat = Math.toRadians((latitude1 - latitude));
        double dLong = Math.toRadians((longitude1 - longitude));

        latitude = Math.toRadians(latitude);
        latitude1 = Math.toRadians(latitude1);

        double a = haversine(dLat) + Math.cos(latitude) * Math.cos(latitude1) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }


}
