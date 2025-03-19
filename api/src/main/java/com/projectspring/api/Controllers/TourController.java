package com.projectspring.api.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.api.Dto.TourDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.TourService;

@RestController
@RequestMapping("/tour")
public class TourController extends GenericController<TourDto, Long, TourService> {

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

}
