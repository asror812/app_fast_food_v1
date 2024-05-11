package com.example.app_fast_food.comment;

import com.example.app_fast_food.comment.dto.CommentCreateDTO;
import com.example.app_fast_food.comment.dto.CommentResponseDTO;
import com.example.app_fast_food.comment.dto.CommentUpdateDTO;
import com.example.app_fast_food.common.mapper.GenericMapper;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;

import java.util.UUID;

public class CommentService  extends GenericService<Comment  , UUID , CommentResponseDTO , CommentCreateDTO , CommentUpdateDTO> {
    @Override
    protected GenericRepository<Comment, UUID> getRepository() {
        return null;
    }

    @Override
    protected Class<Comment> getEntityClass() {
        return null;
    }

    @Override
    protected GenericMapper<Comment, CommentCreateDTO, CommentResponseDTO, CommentUpdateDTO> getMapper() {
        return null;
    }

    @Override
    protected CommonResponse<CommentResponseDTO> internalCreate(CommentCreateDTO commentCreateDTO) {
        return null;
    }

    @Override
    protected CommonResponse<CommentResponseDTO> internalUpdate(UUID uuid, CommentUpdateDTO commentUpdateDTO) {
        return null;
    }
}
