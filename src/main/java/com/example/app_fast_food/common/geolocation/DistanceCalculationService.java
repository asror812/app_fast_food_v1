package com.example.app_fast_food.common.geolocation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


/*
@FeignClient(name = "distanceCalculationFeignClient" , value = "https://geocodeapi.p.rapidapi.com/GetDistance")
public interface DistanceCalculationService {

    @GetMapping
    CalculateResponseDTO calculateDistance(
            @RequestHeader("X-RapidAPI-Key")  String key ,
            @RequestHeader("X-RapidAPI-Host") String host,
            @RequestParam TwoLocations params) ;
}


//"ff8896d7ebmshd0a00ba29ba3322p19c3d0jsnbf1ad971a512"
//"geocodeapi.p.rapidapi.com"*/
