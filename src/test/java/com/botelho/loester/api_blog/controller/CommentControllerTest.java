package com.botelho.loester.api_blog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;
import com.botelho.loester.api_blog.service.CommentService;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentService commentService;

    private UUID id;
    private UUID postId;
    private CommentResponseDto response;

    @BeforeEach
    void setUp() {

        id = UUID.randomUUID();
        postId = UUID.randomUUID();

        response = new CommentResponseDto(
                id,
                "Loester Botelho",
                LocalDate.of(2026, 9, 16),
                "Excelente conteúdo sobre Spring Boot.",
                postId
        );
    }

    @Test
    void deveIncluirComentario() throws Exception {

        when(commentService.createComment(
                eq(postId),
                any(CommentRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "Loester Botelho",
                                    "texto": "Excelente conteúdo sobre Spring Boot."
                                }
                                """)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.texto")
                        .value("Excelente conteúdo sobre Spring Boot."))
                .andExpect(jsonPath("$.postId")
                        .value(postId.toString()));
    }

    @Test
    void deveListarComentariosComPaginacao() throws Exception {

        Pageable pageable =
                PageRequest.of(0, 1);

        PageImpl<CommentResponseDto> page =
                new PageImpl<>(
                        List.of(response),
                        pageable,
                        1
                );

        when(commentService.findAll(any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(
                get("/comments")
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
                .andExpect(jsonPath("$.content[0].texto")
                        .value("Excelente conteúdo sobre Spring Boot."))
                .andExpect(jsonPath("$.content[0].postId")
                        .value(postId.toString()))
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
    void deveListarComentariosDoPostComPaginacao()
            throws Exception {

        Pageable pageable =
                PageRequest.of(0, 1);

        PageImpl<CommentResponseDto> page =
                new PageImpl<>(
                        List.of(response),
                        pageable,
                        1
                );

        when(commentService.findByPostId(
                eq(postId),
                any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(
                get("/comments/post/{postId}", postId)
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
                .andExpect(jsonPath("$.content[0].texto")
                        .value("Excelente conteúdo sobre Spring Boot."))
                .andExpect(jsonPath("$.content[0].postId")
                        .value(postId.toString()))
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
    void deveObterComentarioPorId() throws Exception {

        when(commentService.findById(id))
                .thenReturn(response);

        mockMvc.perform(
                get("/comments/{id}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.texto")
                        .value("Excelente conteúdo sobre Spring Boot."))
                .andExpect(jsonPath("$.postId")
                        .value(postId.toString()));
    }

    @Test
    void deveAtualizarComentario() throws Exception {

        CommentResponseDto responseAtualizado =
                new CommentResponseDto(
                        id,
                        "Loester Botelho",
                        LocalDate.of(2026, 9, 16),
                        "Conteúdo atualizado sobre Spring Boot.",
                        postId
                );

        when(commentService.updateComment(
                eq(id),
                any(CommentRequestDto.class)))
                .thenReturn(responseAtualizado);

        mockMvc.perform(
                put("/comments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "Loester Botelho",
                                    "texto": "Conteúdo atualizado sobre Spring Boot."
                                }
                                """)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(id.toString()))
                .andExpect(jsonPath("$.autor")
                        .value("Loester Botelho"))
                .andExpect(jsonPath("$.data")
                        .value("2026-09-16"))
                .andExpect(jsonPath("$.texto")
                        .value("Conteúdo atualizado sobre Spring Boot."))
                .andExpect(jsonPath("$.postId")
                        .value(postId.toString()));
    }

    @Test
    void deveDeletarComentario() throws Exception {

        doNothing()
                .when(commentService)
                .deleteComment(id);

        mockMvc.perform(
                delete("/comments/{id}", id)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarBadRequestQuandoAutorEstiverVazio()
            throws Exception {

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "",
                                    "texto": "Excelente conteúdo sobre Spring Boot."
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value("autor: Autor é obrigatório"));
    }

    @Test
    void deveRetornarBadRequestQuandoAutorForMuitoCurto()
            throws Exception {

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "AB",
                                    "texto": "Excelente conteúdo sobre Spring Boot."
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "autor: Autor deve possuir entre 3 e 100 caracteres"
                        ));
    }

    @Test
    void deveRetornarBadRequestQuandoTextoEstiverVazio()
            throws Exception {

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "Loester Botelho",
                                    "texto": ""
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value("texto: Texto é obrigatório"));
    }

    @Test
    void deveRetornarBadRequestQuandoTextoForMuitoCurto()
            throws Exception {

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "Loester Botelho",
                                    "texto": "1234"
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "texto: Texto deve possuir entre 5 e 1000 caracteres"
                        ));
    }

    @Test
    void deveRetornarMensagemDeValidacaoEmIngles()
            throws Exception {

        mockMvc.perform(
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "en-US")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "AB",
                                    "texto": "Excelente conteúdo sobre Spring Boot."
                                }
                                """)
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
                post("/comments/post/{postId}", postId)
                        .header("Accept-Language", "pt-BR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "autor": "AB",
                                    "texto": "Excelente conteúdo sobre Spring Boot."
                                }
                                """)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem")
                        .value(
                                "autor: Autor deve possuir entre 3 e 100 caracteres"
                        ));
    }
}