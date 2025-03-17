package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.TourDto;
import com.projectspring.api.Entities.Tour;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.TourMapper;
import com.projectspring.api.Repositories.TourRepository;

@Service
public class TourService extends GenericServiceImpl<Tour, Integer, TourDto, TourRepository, TourMapper> implements GenericService<TourDto, Integer> {

    public TourService(TourRepository repository, TourMapper mapper) {
        super(repository, mapper);
    }
    
}
