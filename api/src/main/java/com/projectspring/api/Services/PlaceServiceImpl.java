package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Place;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceMapper;
import com.projectspring.api.repositories.PlaceRepository;

@Service
public class PlaceServiceImpl
        extends GenericServiceImpl<
        Place,
        PlaceDto,
        PlaceRepository,
        PlaceMapper> implements PlaceService {

    public PlaceServiceImpl(PlaceRepository repository, @Qualifier("placeMapperImpl") PlaceMapper mapper) {
        super(repository, mapper);
    }

    public List<PlaceDto> search(String name) {
        List<Place> places = repository.findByNameStartingWithIgnoreCase(name);
        return places.stream()
                     .map(mapper::toDto)
                     .toList();
    }
    
}
