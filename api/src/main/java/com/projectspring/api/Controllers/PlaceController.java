package com.projectspring.api.Controllers;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.Services.PlaceService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
public class PlaceController extends GenericController<PlaceDto, PlaceService> {

    public PlaceController(PlaceService service) {
        super(service);
    }
}
