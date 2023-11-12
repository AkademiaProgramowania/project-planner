package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.UserDto;

public class UserDtoBuilder {
  private Long id;
  private String email;
  private String name;
  private String password;
  private String passwordRepeated;

  public UserDtoBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public UserDtoBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public UserDtoBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public UserDtoBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  public UserDtoBuilder setPasswordRepeated(String passwordRepeated) {
    this.passwordRepeated = passwordRepeated;
    return this;
  }

  public UserDto build() {
    UserDto userDto = new UserDto();
    userDto.setId(this.id);
    userDto.setEmail(this.email);
    userDto.setName(this.name);
    userDto.setPassword(this.password);
    userDto.setPasswordRepeated(this.passwordRepeated);
    return userDto;
  }
}
