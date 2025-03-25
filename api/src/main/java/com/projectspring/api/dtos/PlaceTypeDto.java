package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.projectspring.api.entities.PlaceType}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class PlaceTypeDto extends BaseDto implements Serializable {
    String name;
<<<<<<< HEAD
    List<PlaceSubTypeDto> placeSubtypes;
    List<PlaceDto> places;
=======
>>>>>>> origin/feat-CREATE-QRCODE
}