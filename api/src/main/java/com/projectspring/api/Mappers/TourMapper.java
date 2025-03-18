package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.TourDto;
import com.projectspring.api.Entities.Tour;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface TourMapper extends GenericMapper<Tour, TourDto> {
    
}
