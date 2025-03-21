package com.projectspring.api.controllers;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.PlaceService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
public class PlaceController extends GenericController<PlaceDto, PlaceService> {

    public PlaceController(PlaceService service) {
        super(service);
    }

    @GetMapping("/search")
    public List<PlaceDto> search(@RequestParam String name) {
        return service.search(name);
    }
}
