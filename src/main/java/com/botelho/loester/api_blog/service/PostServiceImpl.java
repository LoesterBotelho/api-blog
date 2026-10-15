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
import com.botelho.loester.api_blog.model.CategoriaModel;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.CategoriaRepository;
import com.botelho.loester.api_blog.repository.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoriaRepository categoriaRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(
            PostRepository postRepository,
            CategoriaRepository categoriaRepository,
            PostMapper postMapper) {

        this.postRepository = postRepository;
        this.categoriaRepository = categoriaRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable) {

        return postRepository.findAll(pageable)
                .map(postMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(
            Pageable pageable,
            String titulo) {

        if (titulo == null || titulo.isBlank()) {
            return postRepository.findAll(pageable)
                    .map(postMapper::toResponse);
        }

        return postRepository
                .findByTituloContainingIgnoreCase(titulo.trim(), pageable)
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
    public PostResponseDto createPost(PostRequestDto dto) {

        PostModel post = postMapper.toModel(dto);

        CategoriaModel categoria = buscarCategoria(dto.categoriaId());
        post.setCategoria(categoria);

        PostModel postSalvo = postRepository.save(post);

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

        CategoriaModel categoria = buscarCategoria(dto.categoriaId());

        post.setAutor(dto.autor());
        post.setTitulo(dto.titulo());
        post.setTexto(dto.texto());
        post.setCategoria(categoria);

        PostModel postAtualizado = postRepository.save(post);

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

    private CategoriaModel buscarCategoria(UUID categoriaId) {

        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "categoria.notfound",
                                categoriaId
                        )
                );
    }
}