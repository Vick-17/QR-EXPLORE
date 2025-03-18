package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.PlaceSubTypeDto;
import com.projectspring.api.Entities.PlaceSubType;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.PlaceSubTypeMapper;
import com.projectspring.api.Repositories.PlaceSubTypeRepository;

@Service
public class PlaceSubTypeService extends GenericServiceImpl<PlaceSubType, Long, PlaceSubTypeDto, PlaceSubTypeRepository, PlaceSubTypeMapper> implements GenericService<PlaceSubTypeDto, Long> {

    public PlaceSubTypeService(PlaceSubTypeRepository repository, PlaceSubTypeMapper mapper) {
        super(repository, mapper);
    }
    
}
