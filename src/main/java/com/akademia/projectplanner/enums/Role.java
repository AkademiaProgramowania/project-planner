package com.akademia.projectplanner.enums;

public enum Role {
    ADMINISTRATOR("ADMINISTRATOR"),
    ARCHITECT("ARCHITECT"),
    DEVELOPER("DEVELOPER");
    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
