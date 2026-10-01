package com.botelho.loester.api_blog.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;
import com.botelho.loester.api_blog.exception.RegistroNaoEncontradoException;
import com.botelho.loester.api_blog.mapper.CommentMapper;
import com.botelho.loester.api_blog.model.CommentModel;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.CommentRepository;
import com.botelho.loester.api_blog.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private UUID id;
    private UUID postId;
    private PostModel post;
    private CommentModel comment;
    private CommentRequestDto request;
    private CommentResponseDto response;

    @BeforeEach
    void setUp() {

        id = UUID.randomUUID();
        postId = UUID.randomUUID();

        post = new PostModel();

        post.setId(postId);
        post.setAutor("Loester Botelho");
        post.setTitulo("Aprendendo Spring Boot");
        post.setTexto(
                "Conteúdo sobre desenvolvimento de APIs REST."
        );

        comment = new CommentModel();

        comment.setId(id);
        comment.setAutor("Loester Botelho");
        comment.setData(
                LocalDate.of(2026, 9, 16)
        );
        comment.setTexto(
                "Excelente conteúdo sobre Spring Boot."
        );
        comment.setPost(post);

        request = new CommentRequestDto(
                "Loester Botelho",
                "Excelente conteúdo sobre Spring Boot."
        );

        response = new CommentResponseDto(
                id,
                "Loester Botelho",
                LocalDate.of(2026, 9, 16),
                "Excelente conteúdo sobre Spring Boot.",
                postId
        );
    }

    @Test
    void deveIncluirComentario() {

        when(postRepository.findById(postId))
                .thenReturn(Optional.of(post));

        when(commentMapper.toModel(request))
                .thenReturn(comment);

        when(commentRepository.save(comment))
                .thenReturn(comment);

        when(commentMapper.toResponse(comment))
                .thenReturn(response);

        CommentResponseDto resultado =
                commentService.createComment(
                        postId,
                        request
                );

        assertNotNull(resultado);

        assertEquals(
                id,
                resultado.id()
        );

        assertEquals(
                "Loester Botelho",
                resultado.autor()
        );

        assertEquals(
                "Excelente conteúdo sobre Spring Boot.",
                resultado.texto()
        );

        assertEquals(
                postId,
                resultado.postId()
        );

        verify(postRepository)
                .findById(postId);

        verify(commentMapper)
                .toModel(request);

        verify(commentRepository)
                .save(comment);

        verify(commentMapper)
                .toResponse(comment);
    }

    @Test
    void deveLancarExcecaoAoIncluirComentarioEmPostInexistente() {

        when(postRepository.findById(postId))
                .thenReturn(Optional.empty());

        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> commentService.createComment(
                                postId,
                                request
                        )
                );

        assertEquals(
                "post.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{postId},
                exception.getArgs()
        );

        verify(postRepository)
                .findById(postId);

        verify(commentMapper, never())
                .toModel(any());

        verify(commentRepository, never())
                .save(any());
    }

    @Test
    void deveListarComentariosComPaginacao() {

        Pageable pageable =
                PageRequest.of(0, 1);

        Page<CommentModel> page =
                new PageImpl<>(
                        List.of(comment),
                        pageable,
                        1
                );

        when(commentRepository.findAll(pageable))
                .thenReturn(page);

        when(commentMapper.toResponse(comment))
                .thenReturn(response);

        Page<CommentResponseDto> resultado =
                commentService.findAll(pageable);

        assertNotNull(resultado);

        assertEquals(
                1,
                resultado.getTotalElements()
        );

        assertEquals(
                1,
                resultado.getTotalPages()
        );

        assertEquals(
                1,
                resultado.getSize()
        );

        assertEquals(
                0,
                resultado.getNumber()
        );

        assertEquals(
                1,
                resultado.getContent().size()
        );

        assertEquals(
                response,
                resultado.getContent().get(0)
        );

        verify(commentRepository)
                .findAll(pageable);

        verify(commentMapper)
                .toResponse(comment);
    }

    @Test
    void deveListarComentariosDoPostComPaginacao() {

        Pageable pageable =
                PageRequest.of(0, 1);

        Page<CommentModel> page =
                new PageImpl<>(
                        List.of(comment),
                        pageable,
                        1
                );

        when(postRepository.existsById(postId))
                .thenReturn(true);

        when(commentRepository.findByPostId(
                postId,
                pageable))
                .thenReturn(page);

        when(commentMapper.toResponse(comment))
                .thenReturn(response);

        Page<CommentResponseDto> resultado =
                commentService.findByPostId(
                        postId,
                        pageable
                );

        assertNotNull(resultado);

        assertEquals(
                1,
                resultado.getTotalElements()
        );

        assertEquals(
                1,
                resultado.getTotalPages()
        );

        assertEquals(
                1,
                resultado.getSize()
        );

        assertEquals(
                0,
                resultado.getNumber()
        );

        assertEquals(
                response,
                resultado.getContent().get(0)
        );

        verify(postRepository)
                .existsById(postId);

        verify(commentRepository)
                .findByPostId(
                        postId,
                        pageable
                );

        verify(commentMapper)
                .toResponse(comment);
    }

    @Test
    void deveLancarExcecaoAoListarComentariosDePostInexistente() {

        when(postRepository.existsById(postId))
                .thenReturn(false);

        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> commentService.findByPostId(
                                postId,
                                PageRequest.of(0, 1)
                        )
                );

        assertEquals(
                "post.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{postId},
                exception.getArgs()
        );

        verify(postRepository)
                .existsById(postId);

        verify(commentRepository, never())
                .findByPostId(
                        any(UUID.class),
                        any(Pageable.class)
                );
    }

    @Test
    void deveObterComentarioPorId() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));

        when(commentMapper.toResponse(comment))
                .thenReturn(response);

        CommentResponseDto resultado =
                commentService.findById(id);

        assertNotNull(resultado);

        assertEquals(
                id,
                resultado.id()
        );

        assertEquals(
                "Loester Botelho",
                resultado.autor()
        );

        assertEquals(
                "Excelente conteúdo sobre Spring Boot.",
                resultado.texto()
        );

        assertEquals(
                postId,
                resultado.postId()
        );

        verify(commentRepository)
                .findById(id);

        verify(commentMapper)
                .toResponse(comment);
    }

    @Test
    void deveLancarExcecaoQuandoComentarioNaoForEncontrado() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());

        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> commentService.findById(id)
                );

        assertEquals(
                "comment.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(commentRepository)
                .findById(id);

        verify(commentMapper, never())
                .toResponse(any());
    }

    @Test
    void deveAtualizarComentario() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));

        when(commentRepository.save(comment))
                .thenReturn(comment);

        when(commentMapper.toResponse(comment))
                .thenReturn(response);

        CommentResponseDto resultado =
                commentService.updateComment(
                        id,
                        request
                );

        assertNotNull(resultado);

        assertEquals(
                id,
                resultado.id()
        );

        verify(commentRepository)
                .findById(id);

        verify(commentRepository)
                .save(comment);

        verify(commentMapper)
                .toResponse(comment);
    }

    @Test
    void deveLancarExcecaoAoAtualizarComentarioInexistente() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());

        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> commentService.updateComment(
                                id,
                                request
                        )
                );

        assertEquals(
                "comment.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(commentRepository)
                .findById(id);

        verify(commentRepository, never())
                .save(any());
    }

    @Test
    void deveDeletarComentario() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));

        commentService.deleteComment(id);

        verify(commentRepository)
                .findById(id);

        verify(commentRepository)
                .delete(comment);
    }

    @Test
    void deveLancarExcecaoAoDeletarComentarioInexistente() {

        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());

        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> commentService.deleteComment(id)
                );

        assertEquals(
                "comment.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(commentRepository)
                .findById(id);

        verify(commentRepository, never())
                .delete(any());
    }
}