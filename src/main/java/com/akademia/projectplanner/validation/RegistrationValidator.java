package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidator {

  public boolean checkMandatoryFields(UserDto userDto) {
    return userDto.getName().isBlank()
        || userDto.getEmail().isBlank()
        || userDto.getPassword().isBlank();
  }

  public boolean checkPasswordCorrectness(UserDto userDto) {
    return userDto.getPassword().equals(userDto.getPasswordRepeated());
  }
}
