package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.PlaceSubTypeDto;
import com.projectspring.api.Entities.PlaceSubType;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.PlaceSubTypeMapper;
import com.projectspring.api.Repositories.PlaceSubTypeRepositories;

@Service
public class PlaceSubTypeService extends GenericServiceImpl<PlaceSubType, Integer, PlaceSubTypeDto, PlaceSubTypeRepositories, PlaceSubTypeMapper> implements GenericService<PlaceSubTypeDto, Integer> {

    public PlaceSubTypeService(PlaceSubTypeRepositories repository, PlaceSubTypeMapper mapper) {
        super(repository, mapper);
    }
    
}
