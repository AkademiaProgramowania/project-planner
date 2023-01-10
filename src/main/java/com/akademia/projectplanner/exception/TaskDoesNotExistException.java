package com.akademia.projectplanner.exception;

public class TaskDoesNotExistException extends RuntimeException {
  public TaskDoesNotExistException(String message) {
    super(message);
  }
}
