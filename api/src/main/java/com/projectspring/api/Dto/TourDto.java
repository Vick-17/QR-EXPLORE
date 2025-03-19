package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.List;

import com.projectspring.api.Entities.Place;
import com.projectspring.api.Generic.BaseDto;

import jakarta.persistence.OneToMany;

public class TourDto extends BaseDto implements Serializable {
    
    private String name;

    private List<Place> places;
}
