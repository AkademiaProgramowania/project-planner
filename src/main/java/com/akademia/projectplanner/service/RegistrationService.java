package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.RegistrationException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RegistrationService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  @PostConstruct
  void addDummyUser() {
    if (userRepository.count() > 0) {
      return;
    }
    userRepository.save(new UserEntity("Jan", "jan@gmail.com", "password"));
  }

  public void register(UserDto userDto) {
    if (checkMandatoryFieldsInRegistration(userDto)) {
      throw new IllegalArgumentException("Mandatory fields are not filled in!");
    }
    if (userRepository.existsByEmail(userDto.getEmail()) || !checkPasswordCorrectness(userDto)) {
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

  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userEntity -> userMapper.toUserDto(userEntity))
        .collect(Collectors.toList());
  }
}
