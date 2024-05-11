package com.example.app_fast_food.attachment;


import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.common.repository.GenericRepository;
import com.example.app_fast_food.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AttachmentRepository extends GenericRepository<Attachment, UUID> {


    Optional<Attachment> findByOriginalName(String fileName);

   @Query("SELECT at FROM Attachment at WHERE at.product = :productId AND at.isMain = true ")
   Optional<Attachment> findByProduct_IdAndMainIsTrue(UUID productId);

}
