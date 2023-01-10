package com.akademia.projectplanner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

  private String name;
  private String status;
  private String description;
  private String deadline;
}
