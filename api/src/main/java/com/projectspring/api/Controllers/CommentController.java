package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController extends GenericController<CommentDto, Integer, CommentService> {

    public CommentController(CommentService service) {
        super(service);
    }
    
}
