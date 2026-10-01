package com.botelho.loester.api_blog.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;
import com.botelho.loester.api_blog.dto.response.PageResponseDto;
import com.botelho.loester.api_blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
@Tag(
    name = "Posts e Comentários",
    description = "Operações do Blog API"
)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
        summary = "Cria um comentário para um post"
    )
    @PostMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(
            @PathVariable UUID postId,
            @Valid @RequestBody CommentRequestDto dto) {

        return commentService.createComment(postId, dto);
    }

    @Operation(
        summary = "Lista todos os comentários com paginação"
    )
    @GetMapping
    public PageResponseDto<CommentResponseDto> findAll(
            Pageable pageable) {

        Page<CommentResponseDto> result =
                commentService.findAll(pageable);

        return new PageResponseDto<>(
                result.getContent(),
                new PageResponseDto.PageMetadata(
                        result.getSize(),
                        result.getNumber(),
                        result.getTotalElements(),
                        result.getTotalPages()
                )
        );
    }

    @Operation(
        summary = "Lista os comentários de um post com paginação"
    )
    @GetMapping("/post/{postId}")
    public PageResponseDto<CommentResponseDto> findByPostId(
            @PathVariable UUID postId,
            Pageable pageable) {

        Page<CommentResponseDto> result =
                commentService.findByPostId(
                        postId,
                        pageable
                );

        return new PageResponseDto<>(
                result.getContent(),
                new PageResponseDto.PageMetadata(
                        result.getSize(),
                        result.getNumber(),
                        result.getTotalElements(),
                        result.getTotalPages()
                )
        );
    }

    @Operation(
        summary = "Busca um comentário pelo ID"
    )
    @GetMapping("/{id}")
    public CommentResponseDto findById(
            @PathVariable UUID id) {

        return commentService.findById(id);
    }

    @Operation(
        summary = "Atualiza um comentário"
    )
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(
            @PathVariable UUID id,
            @Valid @RequestBody CommentRequestDto dto) {

        return commentService.updateComment(id, dto);
    }

    @Operation(
        summary = "Exclui um comentário"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable UUID id) {

        commentService.deleteComment(id);
    }
}