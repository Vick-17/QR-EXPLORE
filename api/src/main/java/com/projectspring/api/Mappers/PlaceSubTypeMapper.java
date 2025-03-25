package com.projectspring.api.mappers;

import com.projectspring.api.dtos.PlaceSubTypeDto;
import com.projectspring.api.entities.PlaceSubType;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceSubTypeMapper extends GenericMapper<PlaceSubTypeDto, PlaceSubType> {

}
