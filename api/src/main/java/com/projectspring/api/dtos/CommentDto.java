package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO for {@link com.projectspring.api.entities.Comment}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class CommentDto extends BaseDto implements Serializable {
    String description;
    String imageName;
    PlaceDto place;
    UserDto user;
    MultipartFile picture;
}