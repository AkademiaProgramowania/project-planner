package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.TaskDto;

import java.time.LocalDate;

public final class TaskValidator {

  private TaskValidator() {
    throw new UnsupportedOperationException("Cannot be instantiated!");
  }

  public static boolean hasBlankName(TaskDto taskDto) {
    return taskDto.getName().isBlank();
  }

  public static boolean isDeadlineValid(TaskDto taskDto) {
    return taskDto.getDeadline().isBlank()
        || LocalDate.now().isBefore(LocalDate.parse(taskDto.getDeadline()));
  }
}
