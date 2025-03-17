package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.PlaceSubTypeDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.PlaceSubTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/placeSubType")
public class PlaceSubTypeController extends GenericController<PlaceSubTypeDto, Integer, PlaceSubTypeService> {

    public PlaceSubTypeController(PlaceSubTypeService service) {
        super(service);
    }
    
}
