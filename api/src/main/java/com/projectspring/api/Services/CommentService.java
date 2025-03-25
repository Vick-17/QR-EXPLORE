package com.projectspring.api.services;

import java.util.List;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.entities.Comment;
import com.projectspring.api.generic.GenericService;

public interface CommentService extends GenericService<CommentDto> {
    CommentDto postCommentByPlace(CommentDto commentDto, Long placeId);

    CommentDto updateComment(CommentDto commentDto, Long commentId);

    void deleteComment(Long commentId);

    List<Comment> getCommentsByPlace(Long placeId);

    List<Comment> getCommentsByUser();

    byte[] getImage(Long commentId);
}
