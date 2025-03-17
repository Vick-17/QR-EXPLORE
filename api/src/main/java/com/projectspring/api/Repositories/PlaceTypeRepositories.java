package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.PlaceType;

public interface PlaceTypeRepositories extends JpaRepository<PlaceType, Integer> {
    
}
