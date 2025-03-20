package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     
}
