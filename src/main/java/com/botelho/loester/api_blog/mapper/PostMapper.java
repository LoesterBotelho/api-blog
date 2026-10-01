package com.botelho.loester.api_blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.botelho.loester.api_blog.dto.request.PostRequestDto;
import com.botelho.loester.api_blog.dto.response.PostResponseDto;
import com.botelho.loester.api_blog.model.PostModel;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface PostMapper {

    PostResponseDto toResponse(PostModel post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comentarios", ignore = true)
    PostModel toModel(PostRequestDto request);
}