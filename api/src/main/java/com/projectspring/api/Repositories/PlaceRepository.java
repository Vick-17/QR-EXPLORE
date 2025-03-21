package com.projectspring.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByNameStartingWithIgnoreCase(String name);
    
}
