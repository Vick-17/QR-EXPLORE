package com.projectspring.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.Place;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);

    List<Place> findByNameStartingWithIgnoreCase(String name);
}
