package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserEntity toUserEntity(UserDto userDto) {

    if (userDto == null) {
      throw new UserDoesNotExistException("User does not exist!");
    }

    UserEntity userEntity = new UserEntity();

    if (userDto.getId() != null) {
      userEntity.setId(userDto.getId());
    }

    userEntity.setName(userDto.getName());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(userDto.getPassword());

    return userEntity;
  }

  public UserDto toUserDto(UserEntity userEntity) {

    if (userEntity == null) {
      throw new UserDoesNotExistException("User does not exist!");
    }

    UserDto userDto = new UserDto();
    userDto.setId(userEntity.getId());
    userDto.setName(userEntity.getName());
    userDto.setEmail(userEntity.getEmail());
    userDto.setPassword(userEntity.getPassword());

    return userDto;
  }
}
