package com.akademia.projectplanner.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;

@Getter
@Setter
public class TaskDto {

  @NotNull
  private String name;
  private String status;
  private String description;
  private String deadline;
}
