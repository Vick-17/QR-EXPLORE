package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.PlaceDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.PlaceService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController extends GenericController<PlaceDto, Integer, PlaceService> {

    public PlaceController(PlaceService service) {
        super(service);
    }
    
}
