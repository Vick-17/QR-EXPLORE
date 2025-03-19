package com.projectspring.api.Dto;

import java.io.Serializable;

import com.projectspring.api.Controllers.PlaceTypeController;
import com.projectspring.api.Generic.BaseDto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlaceSubTypeDto extends BaseDto implements Serializable {

    private String name;

    private PlaceTypeController placeType;
    
}
