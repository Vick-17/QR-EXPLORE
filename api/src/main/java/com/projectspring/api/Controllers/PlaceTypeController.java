package com.projectspring.api.Controllers;

import com.projectspring.api.dtos.PlaceTypeDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.Services.PlaceTypeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeTypes")
public class PlaceTypeController extends GenericController<PlaceTypeDto, PlaceTypeService> {

    public PlaceTypeController(PlaceTypeService service) {
        super(service);
    }
}
