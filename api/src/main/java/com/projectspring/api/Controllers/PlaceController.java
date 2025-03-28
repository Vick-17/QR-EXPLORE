package com.projectspring.api.controllers;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.PlaceService;

import java.util.List;

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

    @PostMapping(value = "{id}/picture", consumes = "multipart/form-data")
    public ResponseEntity<PlaceDto> addPicture(@PathVariable long id, @ModelAttribute PlaceDto placeDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addPicture(id, placeDto));
    }

    @GetMapping("/search")
    public List<PlaceDto> search(@RequestParam String name) {
        return service.search(name);
    }

}
