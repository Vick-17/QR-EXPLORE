package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.PlaceDto;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.PlaceMapper;
import com.projectspring.api.Repositories.PlaceRepository;

@Service
public class PlaceService extends GenericServiceImpl<Place, Integer, PlaceDto, PlaceRepository, PlaceMapper> implements GenericService<PlaceDto, Integer> {

    public PlaceService(PlaceRepository repository, PlaceMapper mapper) {
        super(repository, mapper);
    }
    
}
