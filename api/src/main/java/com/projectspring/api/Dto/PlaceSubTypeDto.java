package com.projectspring.api.Dto;

import java.io.Serializable;

import com.projectspring.api.Controllers.PlaceTypeController;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlaceSubTypeDto implements Serializable {

    private String name;

    private PlaceTypeController placeType;
    
}
