package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskValidator {

  public boolean checkMandatoryFields(TaskDto taskDto) {
    return taskDto.getName().isBlank() || taskDto.getStatus().isBlank();
  }

  public boolean checkIfDatesAreValid(TaskDto taskDto) {
    return taskDto.getDeadline().isBlank()
        || LocalDate.now().isBefore(LocalDate.parse(taskDto.getDeadline()));
  }
}
