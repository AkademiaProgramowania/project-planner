package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.TaskDto;

import java.time.LocalDate;

public final class TaskValidator {

  private TaskValidator() {
    throw new UnsupportedOperationException("Cannot be instantiated!");
  }

  public static boolean checkMandatoryFields(TaskDto taskDto) {
    return taskDto.getName().isBlank() || taskDto.getStatus().isBlank();
  }

  public static boolean checkIfDatesAreValid(TaskDto taskDto) {
    return taskDto.getDeadline().isBlank()
        || LocalDate.now().isBefore(LocalDate.parse(taskDto.getDeadline()));
  }
}
