package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.projectspring.api.entities.PlaceType}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class PlaceTypeDto extends BaseDto implements Serializable {
    String name;
    List<PlaceSubTypeDto> placeSubtypes;
    List<PlaceDto> places;
}