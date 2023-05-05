package com.akademia.projectplanner.dto;

import com.akademia.projectplanner.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/** A Data Transfer Object (DTO) for a task. */
@Getter
@Setter
public class TaskDto {

  private Long id;

  @NotEmpty(message = "Task name cannot be empty!")
  private String name;

  private StatusEnum statusEnum;
  private String description;
  private String deadline;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  private Long userId;
}
