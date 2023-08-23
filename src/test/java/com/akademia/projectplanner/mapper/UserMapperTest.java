package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private UserDto userDto;
  private UserEntity userEntity;
  private UserMapper userMapper;
  @Mock private UserRepository userRepository;
  @InjectMocks private TaskMapper taskMapper;
  private AutoCloseable autoCloseable;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
    userEntity = new UserEntity();
    autoCloseable = MockitoAnnotations.openMocks(this);
    userMapper = new UserMapper(taskMapper);
  }

  @Test
  void shouldMapUserDtoToUserEntitySuccessfully() {
    // given
    userDto.setId(1L);
    userDto.setEmail("email");
    userDto.setName("name");
    userDto.setPassword("password");
    List<TaskDto> taskDtoList = new ArrayList<>();
    TaskDto taskDto = new TaskDto();
    taskDto.setId(1L);
    taskDtoList.add(taskDto);
    userDto.setTasks(taskDtoList);

    // when
    userEntity = userMapper.toUserEntity(userDto);

    // then
    assertEquals(userDto.getName(), userEntity.getName());
    assertEquals(userDto.getId(), userEntity.getId());
    assertEquals(userDto.getEmail(), userEntity.getEmail());
    assertEquals(userDto.getTasks().get(0).getId(), userEntity.getTasks().get(0).getId());
  }

  @Test
  void shouldThrowExceptionForNullUserDto() {
    // when & then
    assertThrows(UserDoesNotExistException.class, () -> userMapper.toUserEntity(null));
  }

  @Test
  void shouldMapUserEntityToUserDtoSuccessfully() {
    // given
    userEntity.setId(1L);
    userEntity.setEmail("email");
    userEntity.setName("name");
    userEntity.setRole(Role.DEVELOPER);
    TaskEntity taskEntity = new TaskEntity();
    taskEntity.setId(1L);
    List<TaskEntity> taskEntityList = new ArrayList<>();
    taskEntityList.add(taskEntity);
    userEntity.setTasks(taskEntityList);

    // when
    userDto = userMapper.toUserDto(userEntity);

    // then
    assertEquals(userEntity.getName(), userDto.getName());
    assertEquals(userEntity.getId(), userDto.getId());
    assertEquals(userEntity.getEmail(), userDto.getEmail());
    assertEquals(userEntity.getTasks().get(0).getId(), userDto.getTasks().get(0).getId());
  }

  @Test
  void shouldThrowExceptionForNullUserEntity() {
    // when & then
    assertThrows(UserDoesNotExistException.class, () -> userMapper.toUserDto(null));
  }
}
