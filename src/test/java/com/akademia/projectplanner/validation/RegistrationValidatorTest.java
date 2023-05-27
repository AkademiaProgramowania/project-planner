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
  }

  @Test
  void shouldReturnFalseWhenFieldsAreNonBlank() {
    // given
    userDto.setName("username");
    userDto.setEmail("email@gmail.com");
    userDto.setPassword("password");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertFalse(result);
  }

  @Test
  void shouldReturnTrueWhenFieldsAreEmpty() {
    // given
    userDto.setName("");
    userDto.setEmail("");
    userDto.setPassword("");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertTrue(result);
  }

  @Test
  void shouldReturnTrueWhenFieldsAreWhiteSpace() {
    // given
    userDto.setName(" ");
    userDto.setEmail(" ");
    userDto.setPassword(" ");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertTrue(result);
  }

  @Test
  void shouldReturnTrueWhenPasswordCorrect() {
    // given
    userDto.setPassword("password");
    userDto.setPasswordRepeated("password");

    // when
    boolean result = RegistrationValidator.checkPasswordCorrectness(userDto);

    // then
    assertTrue(result);
  }
}
