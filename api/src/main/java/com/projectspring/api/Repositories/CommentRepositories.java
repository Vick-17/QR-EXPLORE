package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Comment;

public interface CommentRepositories extends JpaRepository<Comment, Integer> {
     
}
