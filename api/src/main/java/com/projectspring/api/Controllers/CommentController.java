package com.projectspring.api.controllers;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.entities.Comment;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.mappers.CommentMapper;
import com.projectspring.api.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController extends GenericController<CommentDto, CommentService> {

    private final CommentMapper mapper;

    public CommentController(CommentService service, CommentMapper mapper) {
        super(service);
        this.mapper = mapper;
    }

    @PostMapping(value = "/postCommentByPlace", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto postCommentByPlace(@ModelAttribute CommentDto comment, @RequestParam Long placeId, Long userId) {
        return service.postCommentByPlace(comment, placeId);
    }

    @DeleteMapping(value = "/deleteComment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestParam Long commentId) {
        service.deleteComment(commentId);
    }

    @GetMapping(value = "/getCommentsByPlace")
    public List<CommentDto> getCommentsByPlace(@RequestParam Long placeId) {
        return service.getCommentsByPlace(placeId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping(value = "/getCommentsByUser")
    public List<CommentDto> getCommentsByUser(@RequestParam Long userId) {
        return service.getCommentsByUser(userId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @PutMapping(value = "/updateComment")
    public CommentDto updateComment(@ModelAttribute CommentDto comment, @RequestParam Long commentId) {
        return service.updateComment(comment, commentId);
    }
}
