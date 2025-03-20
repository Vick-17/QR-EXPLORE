package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceSubtypeDto;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.PlaceSubtype;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceSubtypeMapper;
import com.projectspring.api.repositories.PlaceSubTypeRepository;

@Service
public class PlaceSubTypeServiceImpl
        extends GenericServiceImpl<
        PlaceSubtype,
        PlaceSubtypeDto,
        PlaceSubTypeRepository,
        PlaceSubtypeMapper> implements PlaceSubtypeService {

    public PlaceSubTypeServiceImpl(PlaceSubTypeRepository repository, PlaceSubtypeMapper mapper) {
        super(repository, mapper);
    }
    
}
