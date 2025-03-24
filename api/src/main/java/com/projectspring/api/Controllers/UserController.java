package com.projectspring.api.controllers;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.Place;
import com.projectspring.api.entities.User;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserDto, UserService> {

    public UserController(UserService service) {
        super(service);
    }

   @PostMapping("/register")
   public User regiUser(@RequestBody UserDto user) {
       return service.createUser(user);
   }

   @PostMapping("/favorites")
    public ResponseEntity<User> addPlacesToFavorites(@RequestBody List<Long> placeIds) {
        User updatedUser = service.addPlaceToFavorite(placeIds);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/favorites/{placeId}")
    public ResponseEntity<User> removePlaceFromFavorites(@PathVariable Long placeId) {
        User updatedUser = service.removeFavorite(placeId);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/toLater")
    public ResponseEntity<User> addPlacesToLater(@RequestBody List<Long> placeIds) {
        User updatedUser = service.addPlaceToLater(placeIds);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/toLater/{placeId}")
    public ResponseEntity<User> removePlaceFromToLater(@PathVariable Long placeId) {
        User updatedUser = service.removeForLater(placeId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/favorites")
    public ResponseEntity<Set<Place>> getFavorites() {
        Set<Place> places = service.getFavoriteByUserId();
        return ResponseEntity.ok(places);
    }

}
