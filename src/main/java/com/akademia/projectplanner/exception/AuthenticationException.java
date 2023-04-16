package com.akademia.projectplanner.exception;

/** A custom exception class that is used to indicate authentication failures. */
public class AuthenticationException extends RuntimeException {

  /**
   * Constructs a new AuthenticationException with the specified detail message.
   *
   * @param message the detail message
   */
  public AuthenticationException(String message) {
    super(message);
  }
}
