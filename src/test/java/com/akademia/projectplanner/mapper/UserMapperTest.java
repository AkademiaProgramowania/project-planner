package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.UserDto;
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

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private UserDto userDto;
  private UserEntity userEntity;
  private UserMapper userMapper;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
    userEntity = new UserEntity();
    userMapper = new UserMapper();
  }

  @Test
  void shouldMapUserDtoToUserEntitySuccessfully() {
    // given
    userDto.setId(1L);
    userDto.setEmail("email");
    userDto.setName("name");
    userDto.setPassword("password");

    // when
    userEntity = userMapper.toUserEntity(userDto);

    // then
    assertEquals(userDto.getName(), userEntity.getName());
    assertEquals(userDto.getId(), userEntity.getId());
    assertEquals(userDto.getEmail(), userEntity.getEmail());
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

    // when
    userDto = userMapper.toUserDto(userEntity);

    // then
    assertEquals(userEntity.getName(), userDto.getName());
    assertEquals(userEntity.getId(), userDto.getId());
    assertEquals(userEntity.getEmail(), userDto.getEmail());
  }

  @Test
  void shouldThrowExceptionForNullUserEntity() {
    // when & then
    assertThrows(UserDoesNotExistException.class, () -> userMapper.toUserDto(null));
  }
}
