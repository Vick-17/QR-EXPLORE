package com.projectspring.api.Controllers;

import com.projectspring.api.dtos.PlaceSubtypeDto;
import com.projectspring.api.generic.GenericController;

import com.projectspring.api.Services.PlaceSubtypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeSubtypes")
public class PlaceSubTypeController extends GenericController<PlaceSubtypeDto, PlaceSubtypeService> {

    public PlaceSubTypeController(PlaceSubtypeService service) {
        super(service);
    }
}
