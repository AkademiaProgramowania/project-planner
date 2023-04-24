package com.akademia.projectplanner.enums;

public enum StatusEnum {
    TO_DO("To do"),
    IN_PROGRESS("In progress"),
    IN_REVIEW("In review"),
    DONE("Done");
    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
