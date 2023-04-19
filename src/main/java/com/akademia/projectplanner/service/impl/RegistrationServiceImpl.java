package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.service.RegistrationService;
import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.AuthenticationException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
import com.akademia.projectplanner.validation.RegistrationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public void register(UserDto userDto) {
    if (RegistrationValidator.checkMandatoryFields(userDto)) {
      throw new IllegalArgumentException("Mandatory fields are not filled in!");
    }
    if (userRepository.existsByEmail(userDto.getEmail())
        || !RegistrationValidator.checkPasswordCorrectness(userDto)) {
      throw new AuthenticationException("Email or password is not correct!");
    }
    UserEntity userEntity = userMapper.toUserEntity(userDto);
    userRepository.save(userEntity);
  }

  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userEntity -> userMapper.toUserDto(userEntity))
        .collect(Collectors.toList());
  }
}
