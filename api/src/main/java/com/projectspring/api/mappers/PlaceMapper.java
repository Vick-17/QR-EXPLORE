package com.projectspring.api.mappers;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.entities.Place;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper extends GenericMapper<PlaceDto, Place> {

}
