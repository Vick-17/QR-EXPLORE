package com.projectspring.api.services;

import com.projectspring.api.dtos.TourDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projectspring.api.entities.Place;
import com.projectspring.api.entities.Tour;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.TourMapper;
import com.projectspring.api.repositories.PlaceRepository;
import com.projectspring.api.repositories.TourRepository;

@Service
public class TourServiceImpl
        extends GenericServiceImpl<
        Tour,
        TourDto,
        TourRepository,
        TourMapper> implements TourService {

            private final PlaceRepository placeRepository;


    public TourServiceImpl(TourRepository repository, TourMapper mapper, PlaceRepository placeRepository) {
        super(repository, mapper);
        this.placeRepository = placeRepository;
    }

    // Créée un Tour avec plusieur Place
    public TourDto createTour(TourDto tour, List<Long> placeIds) {
        // Vérifier si le Tour existe déjà
        Tour existingTour = repository.findByName(tour.getName());
        if (existingTour != null) {
            throw new IllegalArgumentException("Tour already exists: " + tour.getName());
        }

        // Récupérer les lieux en respectant l'ordre des IDs
        List<Place> places = new ArrayList<>();
        for (Long id : placeIds) {
            placeRepository.findById(id).ifPresent(places::add);
        }

        if (places.isEmpty()) {
            throw new IllegalArgumentException("Aucun lieu trouvé avec les IDs fournis");
        }

        // Création du Tour
        Tour newTour = new Tour();
        newTour.setName(tour.getName());
        // Si isVisible est null, on définit une valeur par défaut à false
        newTour.setIsVisible(tour.getIsVisible() != null ? tour.getIsVisible() : false);
        newTour.setPlaces(places);
        // Sauvegarde en base
        newTour = repository.save(newTour);

        return mapper.toDto(newTour);
    }

    // Modifie un Tour existant en gardant l'ordre des lieux envoyés
    public TourDto updateTour(Long tourId, TourDto tourDto, List<Long> placeIds) {
        Tour existingTour = repository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour introuvable avec l'ID: " + tourId));

        if (!existingTour.getName().equals(tourDto.getName()) && repository.findByName(tourDto.getName()) != null) {
            throw new IllegalArgumentException("Un autre Tour avec ce nom existe déjà: " + tourDto.getName());
        }

        existingTour.setName(tourDto.getName());

        if (placeIds != null && !placeIds.isEmpty()) {
            // Récupérer les lieux SANS GARANTIE d'ordre
            List<Place> places = placeRepository.findAllById(placeIds);

            if (places.isEmpty()) {
                throw new IllegalArgumentException("Aucun lieu trouvé avec les IDs fournis.");
            }

            // Réorganiser les lieux pour correspondre à l'ordre des placeIds envoyés
            Map<Long, Place> placeMap = places.stream()
                    .collect(Collectors.toMap(Place::getId, Function.identity()));

            List<Place> orderedPlaces = new ArrayList<>(placeIds.stream()
                    .map(placeMap::get)
                    .filter(Objects::nonNull)
                    .toList()); // Convertir en ArrayList pour la rendre modifiable

            existingTour.setPlaces(orderedPlaces);
            existingTour.setIsVisible(Boolean.TRUE.equals(tourDto.getIsVisible()));
        }

        existingTour = repository.save(existingTour);

        return mapper.toDto(existingTour);
    }

    // Récupère les tour par Place
    public Tour getTourByPLaceId(Long placeId) {
        return repository.findByPlacesId(placeId);
    }
    
}
