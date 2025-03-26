package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.generic.GenericService;
import org.springframework.web.multipart.MultipartFile;

public interface PlaceService extends GenericService<PlaceDto> {
    PlaceDto createPlace(PlaceDto placeDto);
    void addPicture(long id, MultipartFile picture);
}
