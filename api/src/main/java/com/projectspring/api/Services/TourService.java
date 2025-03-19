package com.projectspring.api.Services;

import java.util.List;
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
public class TourService extends GenericServiceImpl<Tour, Long, TourDto, TourRepository, TourMapper> implements GenericService<TourDto, Long> {

    private final PlaceRepository placeRepository;

    public TourService(TourRepository repository, TourMapper mapper, PlaceRepository placeRepository) {
        super(repository, mapper);
        this.placeRepository = placeRepository;
    }

    

    // Créée un Tour avec plusieur Place
    public TourDto createTour(TourDto tour, List<Long> placeId) {
        Tour existingTour = repository.findByName(tour.getName());
        if (existingTour != null) {
            throw new IllegalArgumentException("Tour already exists: " + tour.getName());
        }

        List<Place> places = placeRepository.findAllById(placeId);

        if (places.isEmpty()) {
            throw new IllegalArgumentException("Aucun lieu trouvé avec les IDs fournis");
        }

        Tour newTour = new Tour();
        newTour.setName(tour.getName());
        newTour.setPlaces(places);

        newTour = repository.save(newTour);

        return new TourDto(newTour.getName(), places.stream().map(Place::getId).toList());        
    }
    
}
