package com.botelho.loester.api_blog.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record CommentResponseDto(
        UUID id,
        String autor,
        LocalDate data,
        String texto,
        UUID postId
) {
}