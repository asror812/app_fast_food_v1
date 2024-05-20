package com.example.app_fast_food.attachment;


import com.example.app_fast_food.attachment.dto.AttachmentResponseDTO;
import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.common.response.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository repository;
    final String IMAGES_FOLDER_PATH = "D:\\Java projects\\fast_food" ;
    final Long limitSize = 1L;

    private final ModelMapper mapper;


    public CommonResponse<AttachmentResponseDTO> uploadImageToFileSystem(HttpServletRequest request) throws IOException, ServletException {


        Part file = request.getPart("file");

        String originalName = file.getSubmittedFileName();
        long size = file.getSize();
        String contentType = file.getContentType();
        UUID id = UUID.randomUUID();
        String downloadUrl = "http://localhost/attachment/download/" + id;


        if(file.getSize()> 1024 * 1024 ){
            throw new RestException.FileSizeLimitExceedException("Image exceeded limit size:  1 mb"  , limitSize );
        }

        Attachment attachment = new Attachment(id , originalName , downloadUrl ,
                contentType , size ,  null );
        repository.save(attachment);

        InputStream inputStream = file.getInputStream();
        Path targetPath = Paths.get(IMAGES_FOLDER_PATH + "/" + id);


        Files.copy(inputStream, targetPath);

        return CommonResponse.succeed(
                mapper.map(file , AttachmentResponseDTO.class)
        );

    }

    public void loadImageFromImageFolder(UUID id, HttpServletResponse response) throws IOException {
        Attachment image = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with name : %s not found"));

        Path  path = Path.of(image.getDownloadUrl());
         try (InputStream inputStream = new FileInputStream(path.toFile())){

             String contentType = Files.probeContentType(path);

             response.setHeader("Content-Disposition", "attachment; filename=\"" + image.getId() + "\"");
             response.setContentType(contentType);

             response.getOutputStream().write(inputStream.readAllBytes());

         }catch (IOException e){
             throw new RestException.FileNotFound(image.getOriginalName() , image.getDownloadUrl() );
         }

    }


    public void delete(UUID id) {
         Attachment entity = repository
                 .findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Attachment with id : %s not found"));

         repository.delete(entity);
    }
}
