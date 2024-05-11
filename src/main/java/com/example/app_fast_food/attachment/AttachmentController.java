package com.example.app_fast_food.attachment;


import com.example.app_fast_food.attachment.dto.AttachmentResponseDTO;
import com.example.app_fast_food.common.response.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {


    private final AttachmentService attachmentService;


    @PostMapping("/upload")
    public CommonResponse<AttachmentResponseDTO> upload(MultipartHttpServletRequest request) {
        try {
            return attachmentService.uploadImageToFileSystem(request);

        } catch (IOException | ServletException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/download/{id}")
    public void load(@PathVariable String id ,
                                     HttpServletResponse response ) {
        try {
            attachmentService.loadImageFromImageFolder(UUID.fromString(id), response);
       } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        attachmentService.delete(UUID.fromString(id));
    }

}
