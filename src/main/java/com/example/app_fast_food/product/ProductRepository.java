package com.example.app_fast_food.product;

import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface ProductRepository extends GenericRepository<Product , UUID> {

    List<Product> findProductsByCategoryName(String name);

    Optional<Product> findProductById(UUID id);
    @Query("SELECT p FROM Product p ORDER BY p.sold desc LIMIT 4")
    List<Product> find4TopSoldProducts();

   @Query(
           value = """
                   SELECT DISTINCT p FROM Product p
                   JOIN p.discounts d
                   JOIN p.bonuses b
                   WHERE d.isActive = true OR b.isActive = true
                   """
   )
   List<Product> getCampaignProducts();


}
