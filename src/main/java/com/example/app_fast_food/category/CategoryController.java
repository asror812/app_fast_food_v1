package com.example.app_fast_food.category;


import com.example.app_fast_food.category.dto.CategoryCreateDTO;
import com.example.app_fast_food.category.dto.CategoryResponseDTO;
import com.example.app_fast_food.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.app_fast_food.common.response.CommonResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDTOMapper mapper;


       @PostMapping
       public CommonResponse<CategoryResponseDTO> create(@RequestBody CategoryCreateDTO createDTO){

           return categoryService.create(createDTO);
       }

       @GetMapping
       public CommonResponse<List<CategoryResponseDTO>> getAll(){
           return categoryService.getAll();
       }


       @GetMapping("{id}")
       public CommonResponse<CategoryResponseDTO> getById(@PathVariable UUID id){
           return categoryService.getById(id);
       }



}
