package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.projectspring.api.entities.Tour}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class TourDto extends BaseDto implements Serializable {
 

    String name;
    Boolean isVisible;

    List<Long> placeIds;
}