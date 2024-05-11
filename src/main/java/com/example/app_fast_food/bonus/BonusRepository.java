package com.example.app_fast_food.bonus;


import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends GenericRepository<Bonus , Long> {

}
