package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.RegistrationException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public void register(UserDto userDto) {
    if (checkMandatoryFieldsInRegistration(userDto)) {
      throw new IllegalArgumentException("Mandatory fields are not filled in!");
    }
    if (userRepository.existsByEmail(userDto.getEmail())
        || !checkPasswordCorrectness(userDto)) {
      throw new RegistrationException("Email or password is not correct!");
    }
    UserEntity userEntity = userMapper.toUserEntity(userDto);
    userRepository.save(userEntity);
  }

  private boolean checkMandatoryFieldsInRegistration(UserDto userDto) {
    return userDto.getName().isBlank()
        || userDto.getEmail().isBlank()
        || userDto.getPassword().isBlank();
  }

  private boolean checkPasswordCorrectness(UserDto userDto) {
    return userDto.getPassword().equals(userDto.getPasswordRepeated());
  }
}
