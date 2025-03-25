package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.entities.PlaceType;
import com.projectspring.api.exceptions.PlaceAlreadyPresentException;
import com.projectspring.api.repositories.PlaceTypeRepository;
import com.projectspring.api.services.Filestorage.FileStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Place;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceMapper;
import com.projectspring.api.repositories.PlaceRepository;

import java.util.Optional;

@Service
public class PlaceServiceImpl
        extends GenericServiceImpl<
        Place,
        PlaceDto,
        PlaceRepository,
        PlaceMapper> implements PlaceService {

    private final FileStorageService fileStorageService;
    private final PlaceTypeRepository placeTypeRepository;

    public PlaceServiceImpl(PlaceRepository repository, @Qualifier("placeMapperImpl") PlaceMapper mapper, FileStorageService fileStorageService, PlaceTypeRepository placeTypeRepository) {
        super(repository, mapper);
        this.fileStorageService = fileStorageService;
        this.placeTypeRepository = placeTypeRepository;
    }

    @Override
    public PlaceDto createPlace(PlaceDto placeDto) {
        Optional<Place> existingPlace = repository.findByName(placeDto.getName());
        if (existingPlace.isPresent()) {
            throw new PlaceAlreadyPresentException("Le lieu ayant pour nom " + placeDto.getName() + " existe déjà.");
        }

        Place place = toEntity(placeDto);

        Optional<PlaceType> placeType = placeTypeRepository.findByName(placeDto.getPlaceType().getName());
        if (placeType.isEmpty()) {
            PlaceType newPlaceType = new PlaceType();
            newPlaceType.setName(placeDto.getPlaceType().getName());
            placeTypeRepository.saveAndFlush(newPlaceType);
            placeType = Optional.of(newPlaceType);
        }

        place.setPlaceType(placeType.get());

        return toDto(repository.saveAndFlush(place));
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
