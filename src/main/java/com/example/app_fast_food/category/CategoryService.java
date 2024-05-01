package com.example.app_fast_food.category;


import com.example.app_fast_food.category.dto.CategoryResponseDTO;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.category.dto.CategoryUpdateDTO;
import com.example.app_fast_food.category.dto.CategoryCreateDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class CategoryService extends GenericService<Category , UUID , CategoryResponseDTO, CategoryCreateDTO , CategoryUpdateDTO> {

    private final CategoryRepository repository;
    private final Class<Category> entityClass = Category.class;
    private final CategoryDTOMapper mapper;

    @Override
    protected CommonResponse<CategoryResponseDTO> internalCreate(CategoryCreateDTO categoryCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<CategoryResponseDTO> internalUpdate(UUID uuid, CategoryUpdateDTO categoryUpdateDTO) {
        return null;
    }
}
