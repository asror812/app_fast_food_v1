package com.example.app_fast_food.user;

import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.user.dto.UserCreateDTO;
import com.example.app_fast_food.user.dto.UserResponseDTO;
import com.example.app_fast_food.user.dto.UserUpdateDTO;
import com.example.app_fast_food.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDTOMapper extends GenericMapper<User, UserCreateDTO , UserResponseDTO , UserUpdateDTO> {

    private final ModelMapper mapper;

    @Override
    public User toEntity(UserCreateDTO createDto) {
        return mapper.map(createDto, User.class) ;
    }

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        return mapper.map(user, UserResponseDTO.class) ;
    }

    @Override
    public void toEntity(UserUpdateDTO updateDto, User user) {
            mapper.map(updateDto, user) ;
    }
}
