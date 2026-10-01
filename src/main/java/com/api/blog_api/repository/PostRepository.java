package com.api.blog_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog_api.model.PostModel;

public interface PostRepository
        extends JpaRepository<PostModel, UUID> {

}