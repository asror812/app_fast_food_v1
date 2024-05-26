package com.example.app_fast_food.discount;

import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DiscountRepository  extends GenericRepository<Discount , UUID> {


        Optional<Discount> findDiscountByName(String name);
}
