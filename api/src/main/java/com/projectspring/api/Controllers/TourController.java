package com.projectspring.api.Controllers;

import com.projectspring.api.dtos.TourDto;
import com.projectspring.api.Services.TourService;
import org.springframework.web.bind.annotation.*;

import com.projectspring.api.generic.GenericController;

@RestController
@RequestMapping("/tours")
public class TourController extends GenericController<TourDto, TourService> {

    public TourController(TourService service) {
        super(service);
    }
}
