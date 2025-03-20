package com.projectspring.api.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.api.Dto.UserDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserDto, Long, UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @PostMapping("/register")
    public UserDto regiUser(@RequestBody UserDto user) {
        return service.createUser(user);
    }

    @PostMapping("/favorites")
    public ResponseEntity<UserDto> addPlacesToFavorites(@RequestBody List<Long> placeIds) {
        UserDto updatedUser = service.addPlaceToFavorite(placeIds);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/favorites/{placeId}")
    public ResponseEntity<UserDto> removePlaceFromFavorites(@PathVariable Long placeId) {
        UserDto updatedUser = service.removeFavorite(placeId);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/toLater")
    public ResponseEntity<UserDto> addPlacesToLater(@RequestBody List<Long> placeIds) {
        UserDto updatedUser = service.addPlaceToLater(placeIds);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/toLater/{placeId}")
    public ResponseEntity<UserDto> removePlaceFromToLater(@PathVariable Long placeId) {
        UserDto updatedUser = service.removeForLater(placeId);
        return ResponseEntity.ok(updatedUser);
    }

}
