package com.projectspring.api.services;

import java.util.List;
import java.util.Set;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.Place;
import com.projectspring.api.entities.User;
import com.projectspring.api.generic.GenericService;

public interface UserService extends GenericService<UserDto> {

    UserDto createUser(UserDto user);

    User removeForLater(Long placeId);

    User addPlaceToLater(List<Long> placeIds);

    User removeFavorite(Long placeId);

    User addPlaceToFavorite(List<Long> placeIds);

    Set<Place> getFavoriteByUserId();

    Set<Place> getToLaterByUserId();
}
