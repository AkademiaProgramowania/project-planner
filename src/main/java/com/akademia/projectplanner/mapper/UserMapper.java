package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.ExceptionMessage;
import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserEntity toUserEntity(UserDto userDto) {

    if (userDto == null) {
      throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
    }

    UserEntity userEntity = new UserEntity();

    if (userDto.getId() != null) {
      userEntity.setId(userDto.getId());
    }

    userEntity.setName(userDto.getName());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
    userEntity.setRole(Role.ADMINISTRATOR);

    return userEntity;
  }

  public UserDto toUserDto(UserEntity userEntity) {

    if (userEntity == null) {
      throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
    }

    UserDto userDto = new UserDto();
    userDto.setId(userEntity.getId());
    userDto.setName(userEntity.getName());
    userDto.setEmail(userEntity.getEmail());
    userDto.setPassword(userEntity.getPassword());
    userDto.setRole(userEntity.getRole());

    return userDto;
  }
}
