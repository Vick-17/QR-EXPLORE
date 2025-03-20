package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    
}
