package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationValidatorTest {

  private UserDto userDto;

  @BeforeEach
  void createUserDto() {
    userDto = new UserDto();
    userDto.setPassword("password");
  }

  @Test
  void shouldReturnFalseWhenFieldsAreNonBlank() {
    // given
    changeUserNameAndEmail("username", "email@gmail.com");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertFalse(result);
  }

  @Test
  void shouldReturnTrueWhenFieldsAreEmpty() {
    // given
    changeUserNameAndEmail("", "");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertTrue(result);
  }

  @Test
  void shouldReturnTrueWhenFieldsAreWhiteSpace() {
    // given
    changeUserNameAndEmail(" ", " ");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertTrue(result);
  }

  @Test
  void shouldReturnTrueWhenPasswordCorrect() {
    // given
    userDto.setPasswordRepeated("password");

    // when
    boolean result = RegistrationValidator.checkPasswordCorrectness(userDto);

    // then
    assertTrue(result);
  }

  private void changeUserNameAndEmail(String name, String email) {
    userDto.setName(name);
    userDto.setEmail(email);
  }
}
