package com.akademia.projectplanner.state;

import com.akademia.projectplanner.dto.UserDto;

import java.time.LocalDateTime;

public class SessionState {
  private UserDto userDto;
  private LocalDateTime dateTime;

  public void login(UserDto userDto) {
    this.userDto = userDto;
    dateTime = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "SessionState{" + "account=" + userDto + ", dateTime=" + dateTime + '}';
  }

  public UserDto getUserDto() {
    return userDto;
  }
}
