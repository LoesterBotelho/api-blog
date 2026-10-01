package com.botelho.loester.api_blog.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;

public interface CommentService {

    Page<CommentResponseDto> findAll(Pageable pageable);

    Page<CommentResponseDto> findByPostId(
            UUID postId,
            Pageable pageable);

    CommentResponseDto findById(UUID id);

    CommentResponseDto createComment(
            UUID postId,
            CommentRequestDto dto);

    CommentResponseDto updateComment(
            UUID id,
            CommentRequestDto dto);

    void deleteComment(UUID id);
}