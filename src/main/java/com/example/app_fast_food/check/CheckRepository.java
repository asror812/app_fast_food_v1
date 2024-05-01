package com.example.app_fast_food.check;


import com.example.app_fast_food.check.entity.Check;
import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CheckRepository extends GenericRepository<Check, UUID> {
}
