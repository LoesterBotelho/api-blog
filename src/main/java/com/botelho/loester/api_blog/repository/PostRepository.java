package com.botelho.loester.api_blog.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.botelho.loester.api_blog.model.PostModel;

public interface PostRepository
        extends JpaRepository<PostModel, UUID> {

    Page<PostModel> findByTituloContainingIgnoreCase(
            String titulo,
            Pageable pageable
    );

}