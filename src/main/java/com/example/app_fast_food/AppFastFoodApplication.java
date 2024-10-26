package com.example.app_fast_food;

import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@EnableScheduling
@RequiredArgsConstructor
public class AppFastFoodApplication {

    private final DiscountRepository discountRepository;
    private final BonusRepository bonusRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppFastFoodApplication.class, args);
    }


    @Scheduled(cron = "0 0 12 * * *")
    public void endDiscountAndBonus(){
        LocalDate today = LocalDate.now();
        List<Discount> all = discountRepository.findAll();
        for (Discount discount : all) {
            if(today.isEqual(discount.getEndDate()) || today.isAfter(discount.getEndDate())){
                discountRepository.delete(discount);
            }
        }

        List<Bonus> bonuses = bonusRepository.findAll();
        for (Bonus bonus : bonuses) {
            if(today.isEqual(bonus.getEndDate()) || today.isAfter(bonus.getEndDate())){
                bonusRepository.delete(bonus);
            }
        }
    }

}
