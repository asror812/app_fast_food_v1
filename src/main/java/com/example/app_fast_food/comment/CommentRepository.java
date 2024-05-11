package com.example.app_fast_food.comment;

import com.example.app_fast_food.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends GenericRepository<Comment , UUID> {
}
