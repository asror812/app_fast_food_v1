package com.example.app_fast_food.category;


import com.example.app_fast_food.category.dto.CategoryCreateDTO;
import com.example.app_fast_food.category.dto.CategoryResponseDTO;
import com.example.app_fast_food.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.app_fast_food.common.response.CommonResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDTOMapper mapper;


   /*    @PostMapping
       public CommonResponse<Category> create(@RequestBody CategoryCreateDTO createDTO){

           CommonResponse<CategoryResponseDTO> categoryResponseDTO = categoryService.create(createDTO);


           return categoryResponseDTO;
       }*/
}
