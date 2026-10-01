package com.botelho.loester.api_blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto(

        @NotBlank(message = "{post.autor.notblank}")
        @Size(
                min = 3,
                max = 100,
                message = "{post.autor.size}"
        )
        String autor,

        @NotBlank(message = "{post.titulo.notblank}")
        @Size(
                min = 3,
                max = 100,
                message = "{post.titulo.size}"
        )
        String titulo,

        @NotBlank(message = "{post.texto.notblank}")
        @Size(
                min = 10,
                message = "{post.texto.size}"
        )
        String texto

) {
}