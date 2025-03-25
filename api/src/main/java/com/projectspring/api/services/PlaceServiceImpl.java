package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.services.Filestorage.FileStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Place;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceMapper;
import com.projectspring.api.repositories.PlaceRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PlaceServiceImpl
        extends GenericServiceImpl<
        Place,
        PlaceDto,
        PlaceRepository,
        PlaceMapper> implements PlaceService {

    private final FileStorageService fileStorageService;

    public PlaceServiceImpl(PlaceRepository repository, @Qualifier("placeMapperImpl") PlaceMapper mapper, FileStorageService fileStorageService) {
        super(repository, mapper);
        this.fileStorageService = fileStorageService;
    }

//    @Override
//    public void addPicture(long id, MultipartFile picture) {
//        repository.findById(id).ifPresent(place -> savePlace(picture, place));
//    }
//
//    private Place savePlace(MultipartFile picture, Place place) {
//        return repository.save(place.setPicture(fileStorageService.store(picture)));
//    }
}
