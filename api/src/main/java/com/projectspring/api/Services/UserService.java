package com.projectspring.api.services;

import java.util.List;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.User;
import com.projectspring.api.generic.GenericService;

public interface UserService extends GenericService<UserDto> {

    User createUser(UserDto user);

    User removeForLater(Long placeId);

    User addPlaceToLater(List<Long> placeIds);

    User removeFavorite(Long placeId);

    User addPlaceToFavorite(List<Long> placeIds);
}
