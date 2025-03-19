package com.projectspring.api.Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Entities.Role;
import com.projectspring.api.Generic.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto implements Serializable {

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserDto(String username2, String email2, String lastName2, String firstName2, Collection<Role> roles2,
            Set<Long> collect) {
    }

    private String username;
    
    private String email;
    
    private String password;

    private String lastName;
    private String firstName;
    private Set<Place> recording = new HashSet<>();


    private Collection<Role> roles = new ArrayList<>();
}
