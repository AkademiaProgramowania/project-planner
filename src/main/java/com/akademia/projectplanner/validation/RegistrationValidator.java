package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.UserDto;

public final class RegistrationValidator {

  RegistrationValidator() {
    throw new UnsupportedOperationException("Cannot be instantiated!");
  }

  public static boolean checkMandatoryFields(UserDto userDto) {
    return userDto.getName().isBlank()
        || userDto.getEmail().isBlank()
        || userDto.getPassword().isBlank();
  }

  public static boolean checkPasswordCorrectness(UserDto userDto) {
    return userDto.getPassword().equals(userDto.getPasswordRepeated());
  }
}
