package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Entities.Comment;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface CommentMapper extends GenericMapper<Comment, CommentDto> {
    
}
