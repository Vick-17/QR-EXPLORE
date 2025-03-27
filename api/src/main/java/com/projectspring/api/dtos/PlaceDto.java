package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link com.projectspring.api.entities.Place}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class PlaceDto extends BaseDto implements Serializable {
    String name;
    String description;
    String location;
    MultipartFile picture;
    String imageName;
    PlaceTypeDto placeType;
}