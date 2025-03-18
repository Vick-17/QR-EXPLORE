package com.projectspring.api.Dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private String description;
    private String imageName;
    
    @JsonIgnore
    private MultipartFile picture;

    private Place place;

    private User user;
    
}
