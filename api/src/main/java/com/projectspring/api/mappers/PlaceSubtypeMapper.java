package com.projectspring.api.mappers;

import com.projectspring.api.dtos.PlaceSubtypeDto;
import com.projectspring.api.entities.PlaceSubtype;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceSubtypeMapper extends GenericMapper<PlaceSubtypeDto, PlaceSubtype> {

}
