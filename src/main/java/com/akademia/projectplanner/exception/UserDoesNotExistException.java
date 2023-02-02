package com.akademia.projectplanner.exception;

public class UserDoesNotExistException extends RuntimeException {
  public UserDoesNotExistException(String message) {
    super(message);
  }
}
