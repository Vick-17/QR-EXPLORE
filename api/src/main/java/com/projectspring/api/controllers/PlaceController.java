package com.projectspring.api.controllers;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.PlaceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
public class PlaceController extends GenericController<PlaceDto, PlaceService> {

    public PlaceController(PlaceService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto) {
        return ResponseEntity
                .status(placeDto.getId() == 0 ? HttpStatus.CREATED : HttpStatus.OK)
                .body(service.createPlace(placeDto));
    }
}
