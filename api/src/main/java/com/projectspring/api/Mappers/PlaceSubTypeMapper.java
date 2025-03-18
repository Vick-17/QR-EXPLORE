package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.PlaceSubTypeDto;
import com.projectspring.api.Entities.PlaceSubType;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface PlaceSubTypeMapper extends GenericMapper<PlaceSubType, PlaceSubTypeDto> {
    
}
