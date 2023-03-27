package com.akademia.projectplanner.enums;

public enum Role {
  ADMINISTRATOR("ROLE_ADMINISTRATOR"),
  ARCHITECT("ROLE_ARCHITECT"),
  DEVELOPER("ROLE_DEVELOPER");
  private String role;

  Role(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
