package com.projectspring.api.services;

import java.util.List;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericService;

public interface PlaceService extends GenericService<PlaceDto> {
    List<PlaceDto> search(String name);
}
