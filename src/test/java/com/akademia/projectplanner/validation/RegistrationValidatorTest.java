package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationValidatorTest {

  private UserDto userDto;

  private void createUserDto() {
    userDto = new UserDto();
    userDto.setPassword("password");
  }

  @Test
  void shouldThrowUnsupportedOperationException() {
    // given
    UnsupportedOperationException exception =
        assertThrows(UnsupportedOperationException.class, RegistrationValidator::new);

    // then
    assertEquals("Cannot be instantiated!", exception.getMessage());
  }

  @Test
  void shouldAssertFalseWhenRegistrationFieldsAreNonBlank() {
    // given
    createUserDto();
    userDto.setName("username");
    userDto.setEmail("email@gmail.com");

    // when
    boolean result = RegistrationValidator.checkMandatoryFields(userDto);

    // then
    assertFalse(result);
  }

  @Test
  void shouldAssertTrueWhenPasswordCorrect() {
    // given
    createUserDto();
    userDto.setPasswordRepeated("password");

    // when
    boolean result = RegistrationValidator.checkPasswordCorrectness(userDto);

    // then
    assertTrue(result);
  }
}
