package com.akademia.projectplanner.enums;

public enum Status {
  TO_DO("toDo"),
  IN_PROGRESS("inProgress"),
  IN_REVIEW("inReview"),
  DONE("done");
  private String status;

  Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
