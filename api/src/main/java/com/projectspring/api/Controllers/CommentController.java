package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Entities.Comment;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/comment")
public class CommentController extends GenericController<CommentDto, Long, CommentService> {

    public CommentController(CommentService service) {
        super(service);
    }

    @PostMapping(value = "/postCommentByPlace", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto postCommentByPlace(@ModelAttribute CommentDto comment, @RequestParam Long placeId) {
        return service.postCommentByPlace(comment, placeId);
    }

    @DeleteMapping(value = "/deleteComment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestParam Long commentId) {
        service.deleteComment(commentId);
    }

    @GetMapping(value = "/getCommentsByPlace")
    public List<Comment> getCommentsByPlace(@RequestParam Long placeId) {
        return service.getCommentsByPlace(placeId);
    }

    @GetMapping(value = "/getCommentsByUser")
    public List<Comment> getCommentsByUser(@RequestParam Long userId) {
        return service.getCommentsByUser(userId);
    }
    
    @PutMapping(value = "/updateComment")
    public CommentDto updateComment(@ModelAttribute CommentDto comment, @RequestParam Long commentId) {
        return service.updateComment(commentId, comment);
    }
}
