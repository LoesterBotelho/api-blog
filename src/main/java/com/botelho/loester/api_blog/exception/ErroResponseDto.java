package com.botelho.loester.api_blog.exception;

import java.time.Instant;

public record ErroResponseDto(
        Integer status, 
        String erro,
        String mensagem,        
        Instant dataHora
) {
}