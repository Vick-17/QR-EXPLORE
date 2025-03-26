package com.projectspring.api.services;

import com.projectspring.api.dtos.PlaceDto;
import com.projectspring.api.entities.PlaceType;
import com.projectspring.api.exceptions.PlaceAlreadyPresentException;
import com.projectspring.api.repositories.PlaceTypeRepository;
import com.projectspring.api.services.Filestorage.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Place;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.PlaceMapper;
import com.projectspring.api.repositories.PlaceRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlaceServiceImpl
        extends GenericServiceImpl<
        Place,
        PlaceDto,
        PlaceRepository,
        PlaceMapper> implements PlaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
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

    @Override
    public void addPicture(long id, MultipartFile picture) {
        repository.findById(id).ifPresent(place -> savePicture(picture, place));
    }

    private void savePicture(MultipartFile picture, Place place) {

        try {
            String imageName = "";
            if (picture != null && !picture.isEmpty()) {
                String storageHash = fileStorageService.getStorageHash(picture).get();
                Path rootLocation = fileStorageService.getRootLocation();
                String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
                storageHash += fileExtension;
                Path saveLocation = rootLocation.resolve(storageHash);
                Files.deleteIfExists(saveLocation);
                Files.copy(picture.getInputStream(), saveLocation);
                imageName = storageHash;
            }

            place.setImageName(imageName);
            place.setPicture(picture);

            repository.saveAndFlush(place);

        } catch (IOException e) {
            LOGGER.error("Erreur lors de la sauvegarde de l'image du lieu", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur lors de la sauvegarde de l'image du lieu");
        }

    }
}
