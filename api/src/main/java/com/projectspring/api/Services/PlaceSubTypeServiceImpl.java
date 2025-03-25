package com.projectspring.api.services;


import org.springframework.stereotype.Service;

import com.projectspring.api.dtos.PlaceSubTypeDto;
import com.projectspring.api.entities.PlaceSubType;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceSubTypeMapper;
import com.projectspring.api.repositories.PlaceSubTypeRepository;

@Service
public class PlaceSubTypeServiceImpl
        extends GenericServiceImpl<
        PlaceSubType,
        PlaceSubTypeDto,
        PlaceSubTypeRepository,
        PlaceSubTypeMapper> implements PlaceSubTypeService {

    public PlaceSubTypeServiceImpl(PlaceSubTypeRepository repository, PlaceSubTypeMapper mapper) {
        super(repository, mapper);
    }
    
}
