package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findByName(String name);

    Tour findByPlacesId(Long placeId);
    
}
