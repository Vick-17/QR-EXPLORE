package com.projectspring.api.controllers;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.CommentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/comments")
public class CommentController extends GenericController<CommentDto, CommentService> {

    public CommentController(CommentService service) {
        super(service);
    }

    //    @PostMapping(value = "/postCommentByPlace", consumes = "multipart/form-data")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CommentDto postCommentByPlace(@ModelAttribute CommentDto comment, @RequestParam Long placeId, Long userId) {
//        return service.postCommentByPlace(comment, placeId, userId);
//    }
    
}
