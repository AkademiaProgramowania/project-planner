package com.akademia.projectplanner.enums;

/** An enumeration of roles that are used to define a user's role in the application. */
public enum Role {
  ADMINISTRATOR("ROLE_ADMINISTRATOR"),
  ARCHITECT("ROLE_ARCHITECT"),
  DEVELOPER("ROLE_DEVELOPER");
  private String role;

  /**
   * Creates a new role
   *
   * @param role the name of the role
   */
  Role(String role) {
    this.role = role;
  }

  /**
   * Returns the name of the role.
   *
   * @return the name of the role
   */
  public String getRole() {
    return role;
  }
}
