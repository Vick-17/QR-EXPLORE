package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

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
    QRCodeDto qrCode;
    PlaceTypeDto placeType;
}