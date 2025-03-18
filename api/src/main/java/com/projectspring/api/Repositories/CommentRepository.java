package com.projectspring.api.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPlaceId(Long placeId);
     
}
