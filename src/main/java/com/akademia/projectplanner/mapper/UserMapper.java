package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.ExceptionMessage;
import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
  private TaskMapper taskMapper;

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
    if (userDto.getTasks() != null) {
      List<TaskEntity> taskEntityList =
          userDto.getTasks().stream()
              .map(taskDto -> taskMapper.toTaskEntity(taskDto))
              .collect(Collectors.toList());
      userEntity.setTasks(taskEntityList);
    } else {
      userEntity.setTasks(Collections.emptyList());
    }

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
    if (userEntity.getTasks() != null) {
      List<TaskDto> taskDtoList =
          userEntity.getTasks().stream()
              .map(taskEntity -> taskMapper.toTaskDto(taskEntity))
              .collect(Collectors.toList());
      userDto.setTasks(taskDtoList);
    } else {
      userDto.setTasks(Collections.emptyList());
    }

    return userDto;
  }
}
