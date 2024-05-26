package com.example.app_fast_food.bonus;


import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BonusRepository extends GenericRepository<Bonus , UUID> {


}
