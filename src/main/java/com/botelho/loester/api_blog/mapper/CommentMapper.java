package com.botelho.loester.api_blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.botelho.loester.api_blog.dto.request.CommentRequestDto;
import com.botelho.loester.api_blog.dto.response.CommentResponseDto;
import com.botelho.loester.api_blog.model.CommentModel;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "postId", source = "post.id")
    CommentResponseDto toResponse(CommentModel comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "data", ignore = true)
    CommentModel toModel(CommentRequestDto request);
}