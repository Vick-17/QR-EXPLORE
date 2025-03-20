package com.projectspring.api.controllers;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.services.UserService;
import org.springframework.web.bind.annotation.*;

import com.projectspring.api.generic.GenericController;


@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserDto, UserService> {

    public UserController(UserService service) {
        super(service);
    }

//    @PostMapping("/register")
//    public UserDto regiUser(@RequestBody UserDto user) {
//        return service.createUser(user);
//    }

}
