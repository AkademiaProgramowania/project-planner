package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.AuthenticationException;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
  private UserRepository userRepository;
  private UserMapper userMapper;

  public LoginServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public void login(UserDto userDto) {
    if (checkMandatoryFieldsInLogin(userDto)) {
      throw new IllegalArgumentException("Mandatory fields are not filled in");
    }
    if (!userRepository.existsByEmail(userDto.getEmail())) {
      throw new UserDoesNotExistException("User with this email does not exist");
    }
    if (!checkPasswordCorrectness(userDto)) {
      throw new AuthenticationException("Password is incorrect");
    }
  }

  private boolean checkMandatoryFieldsInLogin(UserDto userDto) {
    return userDto.getEmail().isBlank() || userDto.getPassword().isBlank();
  }

  private boolean checkPasswordCorrectness(UserDto userDto) {
    UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
    return userEntity.getPassword().equals(userDto.getPassword());
  }
}
