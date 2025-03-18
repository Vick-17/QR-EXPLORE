package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.PlaceTypeDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.PlaceTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/placeType")
public class PlaceTypeController extends GenericController<PlaceTypeDto, Long, PlaceTypeService> {

    public PlaceTypeController(PlaceTypeService service) {
        super(service);
    }
     
}
