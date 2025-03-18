package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.PlaceTypeDto;
import com.projectspring.api.Entities.PlaceType;
import com.projectspring.api.Generic.GenericMapper;



@Mapper
public interface PlaceTypeMapper extends GenericMapper<PlaceType, PlaceTypeDto> {
    
}
