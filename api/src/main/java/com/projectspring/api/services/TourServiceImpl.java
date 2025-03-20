package com.projectspring.api.services;

import com.projectspring.api.dtos.TourDto;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Tour;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.TourMapper;
import com.projectspring.api.repositories.TourRepository;

@Service
public class TourServiceImpl
        extends GenericServiceImpl<
        Tour,
        TourDto,
        TourRepository,
        TourMapper> implements TourService {

    public TourServiceImpl(TourRepository repository, TourMapper mapper) {
        super(repository, mapper);
    }
    
}
