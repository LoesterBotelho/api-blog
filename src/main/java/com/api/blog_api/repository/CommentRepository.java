package com.api.blog_api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog_api.model.CommentModel;

public interface CommentRepository
        extends JpaRepository<CommentModel, UUID> {
	
    Page<CommentModel> findByPostId(
            UUID postId,
            Pageable pageable);
    
}