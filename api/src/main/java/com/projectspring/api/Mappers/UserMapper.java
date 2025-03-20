package com.projectspring.api.mappers;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.User;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper extends GenericMapper<UserDto, User> {

}
