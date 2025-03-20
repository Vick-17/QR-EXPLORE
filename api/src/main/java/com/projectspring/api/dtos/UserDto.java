package com.projectspring.api.dtos;

import com.projectspring.api.generic.BaseDto;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * DTO for {@link com.projectspring.api.entities.User}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class UserDto extends BaseDto implements Serializable {
    String lastName;
    String firstName;
    String username;
    String email;
    String password;
    Collection<RoleDto> roles;
    Set<PlaceDto> recording;
}