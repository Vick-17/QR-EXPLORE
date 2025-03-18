package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectspring.api.Entities.Comment;
import com.projectspring.api.Entities.QRCode;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto implements Serializable {

    private String name;
    private String description;
    private String location;

    @JsonIgnore
    @Transient
    private MultipartFile picture;

    private QRCode qrCode;

    private List<Comment> comments;
    
}
