package com.projectspring.api.mappers;

import com.projectspring.api.dtos.PlaceTypeDto;
import com.projectspring.api.entities.PlaceType;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceTypeMapper extends GenericMapper<PlaceTypeDto, PlaceType> {

}