package com.example.app_fast_food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class AppFastFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppFastFoodApplication.class, args);
    }
    
}
