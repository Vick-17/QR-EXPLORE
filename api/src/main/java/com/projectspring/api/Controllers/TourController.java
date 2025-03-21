package com.projectspring.api.controllers;

import com.projectspring.api.dtos.TourDto;
import com.projectspring.api.entities.Tour;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.TourService;

@RestController
@RequestMapping("/tours")
public class TourController extends GenericController<TourDto, TourService> {

    public TourController(TourService service) {
        super(service);
    }

    @PostMapping("/generateTour")
    public ResponseEntity<TourDto> createTour(@RequestBody TourDto tourDto) {
        if (tourDto.getPlaceIds() == null || tourDto.getPlaceIds().isEmpty()) {
            throw new IllegalArgumentException("La liste des lieux ne peut pas être vide.");
        }
        TourDto createdTour = service.createTour(tourDto, tourDto.getPlaceIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTour);
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<TourDto> updateTour(@PathVariable Long tourId, @RequestBody TourDto tourDto) {
        if (tourDto.getPlaceIds() == null || tourDto.getPlaceIds().isEmpty()) {
            throw new IllegalArgumentException("La liste des lieux ne peut pas être vide.");
        }

        TourDto updatedTour = service.updateTour(tourId, tourDto, tourDto.getPlaceIds());
        return ResponseEntity.ok(updatedTour);
    }

    @GetMapping("/getTour/{placeId}")
    public ResponseEntity<Tour> getTour(@PathVariable Long placeId) {
        Tour tour = service.getTourByPLaceId(placeId);
        return ResponseEntity.ok(tour);
    }

}
