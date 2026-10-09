package com.botelho.loester.api_blog.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.botelho.loester.api_blog.dto.request.PostRequestDto;
import com.botelho.loester.api_blog.dto.response.PostResponseDto;

public interface PostService {

    Page<PostResponseDto> findAll(Pageable pageable);
    Page<PostResponseDto> findAll(Pageable pageable, String titulo);

    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

    PostResponseDto updatePost(
            UUID id,
            PostRequestDto dto);

    void deletePost(UUID id);
}