package com.botelho.loester.api_blog.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;
import com.botelho.loester.api_blog.exception.RegistroNaoEncontradoException;
import com.botelho.loester.api_blog.mapper.CommentMapper;
import com.botelho.loester.api_blog.model.CommentModel;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.CommentRepository;
import com.botelho.loester.api_blog.repository.PostRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository,
            CommentMapper commentMapper) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentResponseDto createComment(
            UUID postId,
            CommentRequestDto dto) {

        PostModel post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "post.notfound",
                                postId
                        )
                );

        CommentModel comment = commentMapper.toModel(dto);
        comment.setPost(post);

        if (comment.getData() == null) {
            comment.setData(LocalDate.now());
        }

        CommentModel commentSalvo =
                commentRepository.save(comment);

        return commentMapper.toResponse(commentSalvo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> findAll(
            Pageable pageable) {

        return commentRepository
                .findAll(pageable)
                .map(commentMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> findByPostId(
            UUID postId,
            Pageable pageable) {

        if (!postRepository.existsById(postId)) {
            throw new RegistroNaoEncontradoException(
                    "post.notfound",
                    postId
            );
        }

        return commentRepository
                .findByPostId(postId, pageable)
                .map(commentMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto findById(UUID id) {

        Optional<CommentModel> optionalComment =
                commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new RegistroNaoEncontradoException(
                    "comment.notfound",
                    id
            );
        }

        return commentMapper.toResponse(
                optionalComment.get()
        );
    }

    @Override
    public CommentResponseDto updateComment(
            UUID id,
            CommentRequestDto dto) {

        CommentModel comment =
                buscarComentarioPorId(id);

        comment.setAutor(dto.autor());
        comment.setTexto(dto.texto());

        CommentModel commentAtualizado =
                commentRepository.save(comment);

        return commentMapper.toResponse(
                commentAtualizado
        );
    }

    @Override
    public void deleteComment(UUID id) {

        CommentModel comment =
                buscarComentarioPorId(id);

        commentRepository.delete(comment);
    }

    private CommentModel buscarComentarioPorId(UUID id) {

        return commentRepository.findById(id)
                .orElseThrow(() ->
                        new RegistroNaoEncontradoException(
                                "comment.notfound",
                                id
                        )
                );
    }
}