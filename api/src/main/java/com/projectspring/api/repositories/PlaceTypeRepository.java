package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.PlaceType;

public interface PlaceTypeRepository extends JpaRepository<PlaceType, Long> {
    
}
