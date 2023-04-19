package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.UserDto;

import java.util.List;

/** The interface used to communication with RegistrationServiceImpl. */
public interface RegistrationService {

  /**
   * Saves newly created user in the database. Method throws IllegalArgumentException if mandatory
   * fields are not filled in, or AuthenticationException if email or password is not correct.
   *
   * @param userDto the UserDto object that contains the form data submitted by the user
   */
  void register(UserDto userDto);

  /**
   * Finds all users in database and maps UserEntity to UserDto.
   *
   * @return the list of UserDto.
   */
  List<UserDto> getAllUsers();
}
