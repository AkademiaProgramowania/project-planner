package com.akademia.projectplanner.validation;

import com.akademia.projectplanner.dto.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskValidatorTest {

  private TaskDto taskDto;

  @BeforeEach
  void beforeEach() {
    taskDto = new TaskDto();
  }

  @Test
  void shouldBeTrueWhenTaskHasStatusAndNameNonBlank() {
    taskDto.setStatus("In progress");
    taskDto.setName("Task number 1");
    assertFalse(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void shouldBeTrueWhenTaskHasBlankNameAndStatus() {
    taskDto.setStatus("");
    taskDto.setName("");

    TaskDto taskWithWhitespace = new TaskDto();
    taskWithWhitespace.setStatus(" ");
    taskWithWhitespace.setName(" ");

    assertAll(
        "Task has blank name or status",
        () -> assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto)),
        () -> assertTrue(TaskValidator.hasBlankNameOrStatus(taskWithWhitespace)));
  }

  @Test
  void shouldBeTrueWhenTaskHasBlankStatusNonBlankName() {
    taskDto.setStatus("");
    taskDto.setName("Test task");
    assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void shouldBeTrueWhenTaskHasBlankNameNonBlankStatus() {
    taskDto.setStatus("In progress");
    taskDto.setName("");
    assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void shouldBeFalseWhenDeadlineYesterday() {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    taskDto.setDeadline(yesterday.toString());
    assertFalse(TaskValidator.isDeadlineValid(taskDto));
  }

  @Test
  void shouldBeTrueWhenDeadlineEmpty() {
    taskDto.setDeadline("");
    assertTrue(TaskValidator.isDeadlineValid(taskDto));
  }

  @Test
  void shouldBeTrueWhenDeadlineTomorrow() {
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    taskDto.setDeadline(tomorrow.toString());
    assertTrue(TaskValidator.isDeadlineValid(taskDto));
  }
}
