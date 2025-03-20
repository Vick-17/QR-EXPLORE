package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.projectspring.api.entities.QRCode}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class QRCodeDto extends BaseDto implements Serializable {

    PlaceDto place;
}