package com.example.app_fast_food.discount;

import com.example.app_fast_food.common.repository.GenericRepository;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DiscountRepository  extends GenericRepository<Discount , UUID> {

        @Query("SELECT d FROM Discount d JOIN d.products p WHERE p.id = :productId")
        List<Discount> findByProductId(@Param("productId") UUID productId);
}
