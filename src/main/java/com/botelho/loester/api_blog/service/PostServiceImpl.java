package com.botelho.loester.api_blog.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.botelho.loester.api_blog.dto.request.PostRequestDto;
import com.botelho.loester.api_blog.dto.response.PostResponseDto;
import com.botelho.loester.api_blog.exception.RegistroNaoEncontradoException;
import com.botelho.loester.api_blog.mapper.PostMapper;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(
            PostRepository postRepository,
            PostMapper postMapper) {

        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(
            Pageable pageable) {

        return postRepository
                .findAll(pageable)
                .map(postMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {

        PostModel post = postRepository.findById(id)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "post.notfound",
                                id
                        )
                );

        return postMapper.toResponse(post);
    }

    @Override
    public PostResponseDto createPost(
            PostRequestDto dto) {

        PostModel post = postMapper.toModel(dto);

        PostModel postSalvo =
                postRepository.save(post);

        return postMapper.toResponse(postSalvo);
    }

    @Override
    public PostResponseDto updatePost(
            UUID id,
            PostRequestDto dto) {

        PostModel post = postRepository.findById(id)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "post.notfound",
                                id
                        )
                );

        post.setAutor(dto.autor());
        post.setTitulo(dto.titulo());
        post.setTexto(dto.texto());

        PostModel postAtualizado =
                postRepository.save(post);

        return postMapper.toResponse(postAtualizado);
    }

    @Override
    public void deletePost(UUID id) {

        PostModel post = postRepository.findById(id)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "post.notfound",
                                id
                        )
                );

        postRepository.delete(post);
    }
}