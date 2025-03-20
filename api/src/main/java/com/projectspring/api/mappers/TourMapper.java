package com.projectspring.api.mappers;

import com.projectspring.api.dtos.TourDto;
import com.projectspring.api.entities.Tour;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface TourMapper extends GenericMapper<TourDto, Tour> {

}
