package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.List;

import com.projectspring.api.Entities.Place;
import com.projectspring.api.Generic.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDto extends BaseDto implements Serializable {
    
    private String name;

    private Boolean isVisible;

    private List<Long> placeIds;
}
