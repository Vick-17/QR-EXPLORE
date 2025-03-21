package com.projectspring.api.services;

import java.util.List;

import com.projectspring.api.dtos.TourDto;
import com.projectspring.api.entities.Tour;
import com.projectspring.api.generic.GenericService;

public interface TourService extends GenericService<TourDto> {
    TourDto createTour(TourDto tour, List<Long> placeIds);

    TourDto updateTour(Long id, TourDto tour, List<Long> placeIds);

    Tour getTourByPLaceId(Long id);
}
