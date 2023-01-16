package com.akademia.projectplanner.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TaskDto {

  @NotEmpty(message = "Task name cannot be empty!")
  private String name;
  private String status;
  private String description;
  private String deadline;
}
