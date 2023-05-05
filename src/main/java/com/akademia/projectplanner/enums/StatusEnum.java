package com.akademia.projectplanner.enums;

public enum StatusEnum {
  TO_DO("toDo"),
  IN_PROGRESS("inProgress"),
  IN_REVIEW("inReview"),
  DONE("done");
  private String status;

  StatusEnum(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
