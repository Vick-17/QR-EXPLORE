package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
    
}
