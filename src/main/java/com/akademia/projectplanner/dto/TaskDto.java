package com.akademia.projectplanner.dto;

import com.akademia.projectplanner.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {

  private Long id;

  @NotEmpty(message = "Task name cannot be empty!")
  private String name;

  private String status;
  private String description;
  private String deadline;
  private LocalDate startDate;
  private UserEntity userEntity;
}
