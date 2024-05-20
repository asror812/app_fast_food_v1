package com.example.app_fast_food.comment;

import com.example.app_fast_food.comment.dto.CommentCreateDTO;
import com.example.app_fast_food.comment.dto.CommentResponseDTO;
import com.example.app_fast_food.comment.dto.CommentUpdateDTO;
import com.example.app_fast_food.common.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CommentDTOMapper extends GenericMapper<Comment , CommentCreateDTO  , CommentResponseDTO , CommentUpdateDTO> {

    private final ModelMapper mapper = new ModelMapper(); ;
    @Override
    public Comment toEntity(CommentCreateDTO commentCreateDTO) {
        return mapper.map(commentCreateDTO, Comment.class);
    }

    @Override
    public CommentResponseDTO toResponseDTO(Comment comment) {
        return mapper.map(comment, CommentResponseDTO.class);
    }

    @Override
    public void toEntity(CommentUpdateDTO commentUpdateDTO, Comment comment) {
     mapper.map(commentUpdateDTO, comment);
    }
}
