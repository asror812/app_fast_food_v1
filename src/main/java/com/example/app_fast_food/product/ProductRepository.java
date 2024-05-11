package com.example.app_fast_food.product;

import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends GenericRepository<Product , UUID> {


    List<Product> findProductsByCategoryName(String name);


    @Query("SELECT p FROM Product p ORDER BY p.sold desc LIMIT 4")
    List<Product> find4TopSoldProducts();

   @Query(
           value = """
                   SELECT DISTINCT p.*
                   FROM fast_food.public.product p WHERE ((SELECT COUNT(*) FROM fast_food.public.product_discount pd WHERE pd.product_id = p.id) > 0\s
                          OR (SELECT COUNT(*) FROM fast_food.public.bonus b WHERE b.product_id = p.id) > 0)"""
            ,nativeQuery = true
   )
    List<Product> getCampaignProducts();
}
