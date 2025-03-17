package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.PlaceDto;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface PlaceMapper extends GenericMapper<Place, PlaceDto> {
    
}
