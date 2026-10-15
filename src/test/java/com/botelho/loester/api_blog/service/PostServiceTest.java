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

import com.botelho.loester.api_blog.dto.request.PostRequestDto;
import com.botelho.loester.api_blog.dto.response.PostResponseDto;
import com.botelho.loester.api_blog.exception.RegistroNaoEncontradoException;
import com.botelho.loester.api_blog.mapper.PostMapper;
import com.botelho.loester.api_blog.model.CategoriaModel;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.CategoriaRepository;
import com.botelho.loester.api_blog.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostServiceImpl postService;

    private UUID id;
    private UUID categoriaId;

    private CategoriaModel categoria;
    private PostModel post;
    private PostRequestDto request;
    private PostResponseDto response;

    @BeforeEach
    void setUp() {

        id = UUID.randomUUID();
        categoriaId = UUID.randomUUID();

        categoria = new CategoriaModel(
                categoriaId,
                "Java"
        );

        post = new PostModel(
                id,
                "Loester Botelho",
                "Aprendendo Spring Boot",
                "Conteúdo sobre desenvolvimento de APIs REST."
        );

        request = new PostRequestDto(
                "Loester Botelho",
                "Aprendendo Spring Boot",
                "Conteúdo sobre desenvolvimento de APIs REST.",
                categoriaId
        );

        response = new PostResponseDto(
                id,
                "Loester Botelho",
                LocalDate.of(2026, 9, 16),
                "Aprendendo Spring Boot",
                "Conteúdo sobre desenvolvimento de APIs REST.",
                categoriaId,
                "Java",
                List.of()
        );
    }

    @Test
    void deveListarPostsComPaginacao() {

        // Arrange
        Pageable pageable = PageRequest.of(0, 1);

        Page<PostModel> page = new PageImpl<>(
                List.of(post),
                pageable,
                1
        );

        when(postRepository.findAll(pageable))
                .thenReturn(page);

        when(postMapper.toResponse(post))
                .thenReturn(response);

        // Act
        Page<PostResponseDto> resultado =
                postService.findAll(pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        assertEquals(1, resultado.getTotalPages());
        assertEquals(1, resultado.getSize());
        assertEquals(0, resultado.getNumber());
        assertEquals(1, resultado.getContent().size());

        assertEquals(
                response,
                resultado.getContent().get(0)
        );

        verify(postRepository).findAll(pageable);
        verify(postMapper).toResponse(post);
    }

    @Test
    void deveObterPostPorId() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.of(post));

        when(postMapper.toResponse(post))
                .thenReturn(response);

        // Act
        PostResponseDto resultado =
                postService.findById(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals("Loester Botelho", resultado.autor());
        assertEquals("Aprendendo Spring Boot", resultado.titulo());
        assertEquals(categoriaId, resultado.categoriaId());
        assertEquals("Java", resultado.categoriaNome());

        verify(postRepository).findById(id);
        verify(postMapper).toResponse(post);
    }

    @Test
    void deveLancarExcecaoQuandoPostNaoForEncontrado() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> postService.findById(id)
                );

        // Assert
        assertEquals(
                "post.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(postRepository).findById(id);

        verify(postMapper, never())
                .toResponse(any());
    }

    @Test
    void deveIncluirPostComCategoria() {

        // Arrange
        when(postMapper.toModel(request))
                .thenReturn(post);

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.of(categoria));

        when(postRepository.save(post))
                .thenReturn(post);

        when(postMapper.toResponse(post))
                .thenReturn(response);

        // Act
        PostResponseDto resultado =
                postService.createPost(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals("Loester Botelho", resultado.autor());
        assertEquals("Aprendendo Spring Boot", resultado.titulo());

        assertEquals(categoriaId, resultado.categoriaId());
        assertEquals("Java", resultado.categoriaNome());
        assertEquals(categoria, post.getCategoria());

        verify(postMapper).toModel(request);
        verify(categoriaRepository).findById(categoriaId);
        verify(postRepository).save(post);
        verify(postMapper).toResponse(post);
    }

    @Test
    void deveLancarExcecaoAoCadastrarPostComCategoriaInexistente() {

        // Arrange
        when(postMapper.toModel(request))
                .thenReturn(post);

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.empty());

        // Act
        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> postService.createPost(request)
                );

        // Assert
        assertEquals(
                "categoria.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{categoriaId},
                exception.getArgs()
        );

        verify(categoriaRepository).findById(categoriaId);
        verify(postRepository, never()).save(any());
        verify(postMapper, never()).toResponse(any());
    }

    @Test
    void deveAtualizarPostComCategoria() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.of(post));

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.of(categoria));

        when(postRepository.save(post))
                .thenReturn(post);

        when(postMapper.toResponse(post))
                .thenReturn(response);

        // Act
        PostResponseDto resultado =
                postService.updatePost(id, request);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals(categoriaId, resultado.categoriaId());
        assertEquals("Java", resultado.categoriaNome());

        assertEquals("Loester Botelho", post.getAutor());
        assertEquals("Aprendendo Spring Boot", post.getTitulo());
        assertEquals(
                "Conteúdo sobre desenvolvimento de APIs REST.",
                post.getTexto()
        );

        assertEquals(categoria, post.getCategoria());

        verify(postRepository).findById(id);
        verify(categoriaRepository).findById(categoriaId);
        verify(postRepository).save(post);
        verify(postMapper).toResponse(post);
    }

    @Test
    void deveLancarExcecaoAoAtualizarPostInexistente() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> postService.updatePost(id, request)
                );

        // Assert
        assertEquals(
                "post.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(postRepository).findById(id);
        verify(categoriaRepository, never()).findById(any());
        verify(postRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoAtualizarComCategoriaInexistente() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.of(post));

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.empty());

        // Act
        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> postService.updatePost(id, request)
                );

        // Assert
        assertEquals(
                "categoria.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{categoriaId},
                exception.getArgs()
        );

        verify(postRepository).findById(id);
        verify(categoriaRepository).findById(categoriaId);
        verify(postRepository, never()).save(any());
    }

    @Test
    void deveDeletarPost() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.of(post));

        // Act
        postService.deletePost(id);

        // Assert
        verify(postRepository).findById(id);
        verify(postRepository).delete(post);
    }

    @Test
    void deveLancarExcecaoAoDeletarPostInexistente() {

        // Arrange
        when(postRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        RegistroNaoEncontradoException exception =
                assertThrows(
                        RegistroNaoEncontradoException.class,
                        () -> postService.deletePost(id)
                );

        // Assert
        assertEquals(
                "post.notfound",
                exception.getMessageKey()
        );

        assertArrayEquals(
                new Object[]{id},
                exception.getArgs()
        );

        verify(postRepository).findById(id);
        verify(postRepository, never()).delete(any());
    }
}