package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceTypeDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.PlaceType;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceTypeMapper;
import com.projectspring.api.repositories.PlaceTypeRepository;

@Service
public class PlaceTypeServiceImpl
        extends GenericServiceImpl<
        PlaceType,
        PlaceTypeDto,
        PlaceTypeRepository,
        PlaceTypeMapper> implements PlaceTypeService {

    public PlaceTypeServiceImpl(PlaceTypeRepository repository, @Qualifier("placeTypeMapperImpl") PlaceTypeMapper mapper) {
        super(repository, mapper);
    }
    
}
