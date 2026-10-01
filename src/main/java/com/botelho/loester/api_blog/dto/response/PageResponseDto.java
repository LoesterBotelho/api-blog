package com.botelho.loester.api_blog.dto.response;

import java.util.List;

public record PageResponseDto<T>(
        List<T> content,
        PageMetadata page
) {

    public record PageMetadata(
            int size,
            int number,
            long totalElements,
            int totalPages
    ) {
    }
}