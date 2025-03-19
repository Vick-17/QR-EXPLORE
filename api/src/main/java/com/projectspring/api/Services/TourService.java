package com.projectspring.api.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.TourDto;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Entities.Tour;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.TourMapper;
import com.projectspring.api.Repositories.PlaceRepository;
import com.projectspring.api.Repositories.TourRepository;

@Service
public class TourService extends GenericServiceImpl<Tour, Long, TourDto, TourRepository, TourMapper>
        implements GenericService<TourDto, Long> {

    private final PlaceRepository placeRepository;

    public TourService(TourRepository repository, TourMapper mapper, PlaceRepository placeRepository) {
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
        newTour.setPlaces(places);

        // Sauvegarde en base
        newTour = repository.save(newTour);

        return new TourDto(newTour.getName(), newTour.getPlaces().stream().map(Place::getId).toList());
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
        }

        existingTour = repository.save(existingTour);

        return new TourDto(existingTour.getName(), existingTour.getPlaces().stream().map(Place::getId).toList());
    }

}
