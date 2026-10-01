package com.api.blog_api.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.PostResponseDto;

public interface PostService {

    Page<PostResponseDto> findAll(Pageable pageable);

    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

    PostResponseDto updatePost(
            UUID id,
            PostRequestDto dto);

    void deletePost(UUID id);
}