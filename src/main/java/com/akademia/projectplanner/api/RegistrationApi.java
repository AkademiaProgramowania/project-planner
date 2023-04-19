package com.akademia.projectplanner.api;

import com.akademia.projectplanner.dto.UserDto;

import java.util.List;

public interface RegistrationApi {

    void register(UserDto userDto);
    List<UserDto> getAllUsers();
}
