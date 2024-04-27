package com.example.app_fast_food.user;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.user.dto.UserCreateDTO;
import com.example.app_fast_food.user.dto.UserResponseDTO;
import com.example.app_fast_food.user.dto.UserUpdateDTO;
import com.example.app_fast_food.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends GenericService<User, UUID , UserResponseDTO, UserCreateDTO, UserUpdateDTO> {
    @Override
    protected GenericRepository<User, UUID> getRepository() {
        return null;
    }

    @Override
    protected Class<User> getEntityClass() {
        return null;
    }

    @Override
    protected GenericMapper<User, UserCreateDTO, UserResponseDTO, UserUpdateDTO> getMapper() {
        return null;
    }

    @Override
    protected UserResponseDTO internalCreate(UserCreateDTO userCreateDTO) {
        return null;
    }

    @Override
    protected UserResponseDTO internalUpdate(UUID uuid, UserUpdateDTO userUpdateDTO) {
        return null;
    }
}
