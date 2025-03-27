package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.PlaceType;

import java.util.Optional;

public interface PlaceTypeRepository extends JpaRepository<PlaceType, Long> {

    Optional<PlaceType> findByName(String name);
}
