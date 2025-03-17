package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.List;

public class PlaceTypeDto implements Serializable {

    private String name;

    private List<PlaceSubTypeDto> subTypes;
    
}
