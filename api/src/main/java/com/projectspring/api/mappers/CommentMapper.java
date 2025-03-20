package com.projectspring.api.mappers;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.entities.Comment;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper extends GenericMapper<CommentDto, Comment> {

}