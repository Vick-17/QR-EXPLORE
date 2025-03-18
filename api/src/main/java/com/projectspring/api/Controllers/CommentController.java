package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/comment")
public class CommentController extends GenericController<CommentDto, Integer, CommentService> {

    public CommentController(CommentService service) {
        super(service);
    }

    @PostMapping(value = "/postCommentByPlace", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto postCommentByPlace(@ModelAttribute CommentDto comment, @RequestParam int placeId, int userId) {
        return service.postCommentByPlace(comment, placeId, userId);
    }
    
}
