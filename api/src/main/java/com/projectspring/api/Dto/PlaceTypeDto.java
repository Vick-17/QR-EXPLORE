package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.List;

import com.projectspring.api.Generic.BaseDto;

public class PlaceTypeDto extends BaseDto implements Serializable {

    private String name;

    private List<PlaceSubTypeDto> subTypes;
    
}
