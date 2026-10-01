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

import com.botelho.loester.api_blog.dto.request.PostRequestDto;
import com.botelho.loester.api_blog.dto.response.PageResponseDto;
import com.botelho.loester.api_blog.dto.response.PostResponseDto;
import com.botelho.loester.api_blog.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
@Tag(
    name = "Posts e Comentários",
    description = "Operações do Blog API"
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
        summary = "Lista posts com paginação"
    )
    @GetMapping
    public PageResponseDto<PostResponseDto> findAll(
            Pageable pageable) {

        Page<PostResponseDto> result =
                postService.findAll(pageable);

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
        summary = "Busca um post pelo ID"
    )
    @GetMapping("/{id}")
    public PostResponseDto findById(
            @PathVariable UUID id) {

        return postService.findById(id);
    }

    @Operation(
        summary = "Cadastra um novo post"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createPost(
            @Valid @RequestBody PostRequestDto dto) {

        return postService.createPost(dto);
    }

    @Operation(
        summary = "Atualiza um post"
    )
    @PutMapping("/{id}")
    public PostResponseDto updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody PostRequestDto dto) {

        return postService.updatePost(id, dto);
    }

    @Operation(
        summary = "Exclui um post"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable UUID id) {

        postService.deletePost(id);
    }
}