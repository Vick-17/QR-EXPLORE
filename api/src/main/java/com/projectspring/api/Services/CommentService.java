package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Entities.Comment;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.CommentMapper;
import com.projectspring.api.Repositories.CommentRepository;

@Service
public class CommentService extends GenericServiceImpl<Comment, Integer, CommentDto, CommentRepository, CommentMapper> implements GenericService<CommentDto, Integer> {

    public CommentService(CommentRepository repository, CommentMapper mapper) {
        super(repository, mapper);
    }
    
}
