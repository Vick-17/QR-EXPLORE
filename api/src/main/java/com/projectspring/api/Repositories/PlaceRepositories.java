package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Place;

public interface PlaceRepositories extends JpaRepository<Place, Integer> {
    
}
