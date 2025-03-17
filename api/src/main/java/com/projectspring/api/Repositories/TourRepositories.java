package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Tour;

public interface TourRepositories extends JpaRepository<Tour, Integer> {
    
}
