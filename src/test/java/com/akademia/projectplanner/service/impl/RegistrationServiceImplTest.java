package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.AuthenticationException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RegistrationServiceImplTest {

  @Mock private UserRepository userRepository;
  @Mock private UserMapper userMapper;
  @InjectMocks private RegistrationServiceImpl registrationService;
  private AutoCloseable autoCloseable;
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
    userDto = buildUserDto();
  }

  @Test
  void shouldRegisterSuccessfully() {
    // given
    UserEntity userEntity = new UserEntity();
    Mockito.when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
    Mockito.when(userMapper.toUserEntity(userDto)).thenReturn(userEntity);
    // when

    registrationService.register(userDto);
    // then
    verify(userRepository, times(1)).save(userEntity);
  }

  @Test
  void shouldThrowAuthenticationExceptionWhenEmailExists() throws AuthenticationException {
    // given
    UserEntity userEntity = new UserEntity();
    Mockito.when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);
    Mockito.when(userMapper.toUserEntity(userDto)).thenReturn(userEntity);

    // when & then
    assertThrows(AuthenticationException.class, () -> registrationService.register(userDto));
  }

  @Test
  void shouldThrowAuthenticationExceptionWhenPasswordNotEqual() throws AuthenticationException {
    // given
    userDto.setPassword("pass");
    userDto.setPasswordRepeated("password");
    UserEntity userEntity = new UserEntity();
    Mockito.when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
    Mockito.when(userMapper.toUserEntity(userDto)).thenReturn(userEntity);

    // when & then
    assertThrows(AuthenticationException.class, () -> registrationService.register(userDto));
  }

  @Test
  void getAllUsers() {
    // given
    List<UserEntity> userEntities = new ArrayList<>();
    userEntities.add(new UserEntity());

    Mockito.when(userRepository.findAll()).thenReturn(userEntities);

    UserDto userDto = new UserDto();
    Mockito.when(userMapper.toUserDto(Mockito.any(UserEntity.class))).thenReturn(userDto);

    // When
    List<UserDto> result = registrationService.getAllUsers();

    // Then
    assertNotNull(result);
    assertEquals(userEntities.size(), result.size());
  }

  private UserDto buildUserDto() {
    return new UserDtoBuilder()
        .setId(1L)
        .setEmail("email")
        .setName("name")
        .setPassword("pass")
        .setPasswordRepeated("pass")
        .build();
  }
}
