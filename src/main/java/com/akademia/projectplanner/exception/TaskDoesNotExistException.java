package com.akademia.projectplanner.exception;

/**
 * A custom exception class that is used to indicate that the user is trying to access a task that
 * does not exist in the system.
 */
public class TaskDoesNotExistException extends RuntimeException {

  /**
   * Constructs a new TaskDoesNotExistException with the specified detail message.
   *
   * @param message the detail message
   */
  public TaskDoesNotExistException(String message) {
    super(message);
  }
}
