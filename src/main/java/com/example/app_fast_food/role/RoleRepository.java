package com.example.app_fast_food.role;

import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends GenericRepository<Role, String> {
}
