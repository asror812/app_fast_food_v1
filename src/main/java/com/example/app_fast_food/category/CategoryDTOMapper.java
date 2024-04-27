package com.example.app_fast_food.category;


import com.example.app_fast_food.category.dto.CategoryCreateDTO;
import com.example.app_fast_food.category.dto.CategoryDTO;
import com.example.app_fast_food.category.dto.CategoryResponseDTO;
import com.example.app_fast_food.category.dto.CategoryUpdateDTO;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.common.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDTOMapper extends GenericMapper<Category, CategoryCreateDTO, CategoryResponseDTO, CategoryUpdateDTO> {

    private final ModelMapper mapper;

    @Override
    public Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        return mapper.map(categoryCreateDTO, Category.class);
    }

    @Override
    public CategoryResponseDTO toResponseDTO(Category category) {
        return mapper.map(category , CategoryResponseDTO.class);
    }

    @Override
    public void toEntity(CategoryUpdateDTO categoryUpdateDTO, Category category) {
           mapper.map(categoryUpdateDTO, category);
    }
}
