package com.projectspring.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPlaceId(Long placeId);

    List<Comment> findByUserId(Long userId);
     
}
