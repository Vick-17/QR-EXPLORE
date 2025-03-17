package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.PlaceTypeDto;
import com.projectspring.api.Entities.PlaceType;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.PlaceTypeMapper;
import com.projectspring.api.Repositories.PlaceTypeRepository;

@Service
public class PlaceTypeService extends GenericServiceImpl<PlaceType, Integer, PlaceTypeDto, PlaceTypeRepository, PlaceTypeMapper> implements GenericService<PlaceTypeDto, Integer> {

    public PlaceTypeService(PlaceTypeRepository repository, PlaceTypeMapper mapper) {
        super(repository, mapper);
    }
    
}
