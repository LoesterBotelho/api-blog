package com.botelho.loester.api_blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequestDto(

        @NotBlank(message = "{comment.autor.notblank}")
        @Size(
                min = 3,
                max = 100,
                message = "{comment.autor.size}"
        )
        String autor,

        @NotBlank(message = "{comment.texto.notblank}")
        @Size(
                min = 5,
                max = 1000,
                message = "{comment.texto.size}"
        )
        String texto

) {
}