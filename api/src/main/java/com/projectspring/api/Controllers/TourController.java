package com.projectspring.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.api.Dto.TourDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.TourService;

@RestController
@RequestMapping("/tour")
public class TourController extends GenericController<TourDto, Integer, TourService> {

    public TourController(TourService service) {
        super(service);
    }
    
}
