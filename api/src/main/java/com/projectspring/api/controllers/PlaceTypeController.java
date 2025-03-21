package com.projectspring.api.controllers;

import com.projectspring.api.dtos.PlaceTypeDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.PlaceTypeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placetypes")
public class PlaceTypeController extends GenericController<PlaceTypeDto, PlaceTypeService> {

    public PlaceTypeController(PlaceTypeService service) {
        super(service);
    }
}
