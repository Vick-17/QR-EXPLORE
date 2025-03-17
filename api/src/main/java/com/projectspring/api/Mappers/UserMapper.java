package com.projectspring.api.Mappers;


import com.projectspring.api.Entities.User;
import org.mapstruct.Mapper;

import com.projectspring.api.Dto.UserDto;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDto> {
    
}
