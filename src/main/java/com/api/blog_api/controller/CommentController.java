package com.api.blog_api.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog_api.dto.request.CommentRequestDto;
import com.api.blog_api.dto.response.CommentResponseDto;
import com.api.blog_api.service.CommentService;

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
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable UUID postId,
            @Valid @RequestBody CommentRequestDto dto) {

        CommentResponseDto comment =
                commentService.createComment(postId, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

    @Operation(
        summary = "Lista todos os comentários com paginação"
    )
    @GetMapping
    public ResponseEntity<Page<CommentResponseDto>> findAll(
            Pageable pageable) {

        return ResponseEntity.ok(
                commentService.findAll(pageable)
        );
    }

    @Operation(
        summary = "Lista os comentários de um post com paginação"
    )
    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<CommentResponseDto>> findByPostId(
            @PathVariable UUID postId,
            Pageable pageable) {

        return ResponseEntity.ok(
                commentService.findByPostId(
                        postId,
                        pageable
                )
        );
    }

    @Operation(
        summary = "Busca um comentário pelo ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                commentService.findById(id)
        );
    }

    @Operation(
        summary = "Atualiza um comentário"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable UUID id,
            @Valid @RequestBody CommentRequestDto dto) {

        return ResponseEntity.ok(
                commentService.updateComment(id, dto)
        );
    }

    @Operation(
        summary = "Exclui um comentário"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable UUID id) {

        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}