package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link PlaceSubtype}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class PlaceSubtypeDto extends BaseDto implements Serializable {
    String name;
    PlaceTypeDto placeType;
}