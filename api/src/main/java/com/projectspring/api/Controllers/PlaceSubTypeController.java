package com.projectspring.api.controllers;

import com.projectspring.api.dtos.PlaceSubTypeDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.PlaceSubTypeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeSubtypes")
public class PlaceSubTypeController extends GenericController<PlaceSubTypeDto, PlaceSubTypeService> {

    public PlaceSubTypeController(PlaceSubTypeService service) {
        super(service);
    }
}
