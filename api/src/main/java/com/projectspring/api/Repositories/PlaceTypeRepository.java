package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.PlaceType;

public interface PlaceTypeRepository extends JpaRepository<PlaceType, Long> {
    
}
