package com.projectspring.api.Dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectspring.api.Entities.Place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private int id;
    private String description;
    private String imageName;
    
    @JsonIgnore
    private MultipartFile picture;

    private Place place;
    
}
