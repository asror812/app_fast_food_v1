package com.example.app_fast_food.attachment;

import com.example.app_fast_food.attachment.dto.AttachmentResponseDTO;
import com.example.app_fast_food.attachment.entity.Attachment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AttachmentDTOMapper {
    private final ModelMapper modelMapper ;


    public AttachmentResponseDTO toResponseDTO(Attachment attachment) {
        return modelMapper.map(attachment, AttachmentResponseDTO.class);
    }
}
