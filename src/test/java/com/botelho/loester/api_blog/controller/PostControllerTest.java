package com.botelho.loester.api_blog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.botelho.loester.api_blog.dto.response.PostResponseDto;
import com.botelho.loester.api_blog.service.PostService;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    private UUID id;
    private UUID categoriaId;
    private PostResponseDto response;

    @BeforeEach
    void setUp() {

        id = UUID.randomUUID();
        categoriaId = UUID.randomUUID();

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

    private String jsonPost(
            String autor,
            String titulo,
            String texto) {

        return """
                {
                    "autor": "%s",
                    "titulo": "%s",
                    "texto": "%s",
                    "categoriaId": "%s"
                }
                """.formatted(
                        autor,
                        titulo,
                        texto,
                        categoriaId
                );
    }

    @Test
    void deveListarPostsComPaginacao() throws Exception {

        // Arrange
        Pageable pageable = PageRequest.of(0, 1);

        PageImpl<PostResponseDto> page = new PageImpl<>(
                List.of(response),
                pageable,
                1
        );

        when(postService.findAll(
                any(Pageable.class),
                nullable(String.class)))
                .thenReturn(page);

        // Act / Assert
        mockMvc.perform(
                get("/posts")
                        .param("page", "0")
                        .param("size", "1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.content[0].autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.content[0].data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.content[0].titulo")
                        .value("Aprendendo Spring Boot"))
                .andExpect(jsonPath("$.content[0].texto")
                        .value("Conteúdo sobre desenvolvimento de APIs REST."))
                .andExpect(jsonPath("$.content[0].categoriaId")
                        .value(categoriaId.toString()))
                .andExpect(jsonPath("$.content[0].categoriaNome")
                        .value("Java"))
                .andExpect(jsonPath("$.page.size")
                        .value(1))
                .andExpect(jsonPath("$.page.number")
                        .value(0))
                .andExpect(jsonPath("$.page.totalElements")
                        .value(1))
                .andExpect(jsonPath("$.page.totalPages")
                        .value(1));
    }

    @Test
    void deveObterPostPorId() throws Exception {

        // Arrange
        when(postService.findById(id))
                .thenReturn(response);

        // Act / Assert
        mockMvc.perform(
                get("/posts/{id}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.titulo")
                        .value("Aprendendo Spring Boot"))
                .andExpect(jsonPath("$.texto")
                        .value("Conteúdo sobre desenvolvimento de APIs REST."))
                .andExpect(jsonPath("$.categoriaId")
                        .value(categoriaId.toString()))
                .andExpect(jsonPath("$.categoriaNome")
                        .value("Java"));
    }

    @Test
    void deveIncluirPostComCategoria() throws Exception {

        // Arrange
        when(postService.createPost(any()))
                .thenReturn(response);

        // Act / Assert
        mockMvc.perform(
                post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "Aprendendo Spring Boot",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.titulo")
                        .value("Aprendendo Spring Boot"))
                .andExpect(jsonPath("$.texto")
                        .value("Conteúdo sobre desenvolvimento de APIs REST."))
                .andExpect(jsonPath("$.categoriaId")
                        .value(categoriaId.toString()))
                .andExpect(jsonPath("$.categoriaNome")
                        .value("Java"));
    }

    @Test
    void deveAtualizarPostComCategoria() throws Exception {

        // Arrange
        PostResponseDto responseAtualizado =
                new PostResponseDto(
                        id,
                        "Loester Botelho",
                        LocalDate.of(2026, 9, 16),
                        "Aprendendo Spring Boot",
                        "Conteúdo atualizado sobre desenvolvimento de APIs REST.",
                        categoriaId,
                        "Java",
                        List.of()
                );

        when(postService.updatePost(
                eq(id),
                any()))
                .thenReturn(responseAtualizado);

        // Act / Assert
        mockMvc.perform(
                put("/posts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "Aprendendo Spring Boot",
                                "Conteúdo atualizado sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.titulo")
                        .value("Aprendendo Spring Boot"))
                .andExpect(jsonPath("$.texto")
                        .value(
                                "Conteúdo atualizado sobre desenvolvimento de APIs REST."
                        ))
                .andExpect(jsonPath("$.categoriaId")
                        .value(categoriaId.toString()))
                .andExpect(jsonPath("$.categoriaNome")
                        .value("Java"));
    }

    @Test
    void deveDeletarPost() throws Exception {

        // Arrange
        doNothing()
                .when(postService)
                .deletePost(id);

        // Act / Assert
        mockMvc.perform(
                delete("/posts/{id}", id)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarBadRequestQuandoAutorEstiverVazio()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "",
                                "Aprendendo Spring Boot",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value("autor: Autor é obrigatório"));
    }

    @Test
    void deveRetornarBadRequestQuandoAutorForMuitoCurto()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "AB",
                                "Aprendendo Spring Boot",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "autor: Autor deve possuir entre 3 e 100 caracteres"
                        ));
    }

    @Test
    void deveRetornarBadRequestQuandoTituloEstiverVazio()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value("titulo: Título é obrigatório"));
    }

    @Test
    void deveRetornarBadRequestQuandoTituloForMuitoCurto()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "AB",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "titulo: Título deve possuir entre 3 e 100 caracteres"
                        ));
    }

    @Test
    void deveRetornarBadRequestQuandoTextoEstiverVazio()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "Aprendendo Spring Boot",
                                ""
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value("texto: Texto é obrigatório"));
    }

    @Test
    void deveRetornarBadRequestQuandoTextoForMuitoCurto()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "Loester Botelho",
                                "Aprendendo Spring Boot",
                                "123456789"
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "texto: Texto deve possuir no mínimo 10 caracteres"
                        ));
    }

    @Test
    void deveRetornarBadRequestQuandoCategoriaNaoForInformada()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "Loester Botelho",
                                    "titulo": "Aprendendo Spring Boot",
                                    "texto": "Conteúdo sobre desenvolvimento de APIs REST."
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "categoriaId: Categoria é obrigatória"
                        ));
    }

    @Test
    void deveRetornarMensagemDeValidacaoEmIngles()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "en-US")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "AB",
                                "Aprendendo Spring Boot",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "autor: Author must contain between 3 and 100 characters"
                        ));
    }

    @Test
    void deveRetornarMensagemDeValidacaoEmPortugues()
            throws Exception {

        mockMvc.perform(
                post("/posts")
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost(
                                "AB",
                                "Aprendendo Spring Boot",
                                "Conteúdo sobre desenvolvimento de APIs REST."
                        ))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "autor: Autor deve possuir entre 3 e 100 caracteres"
                        ));
    }
}
