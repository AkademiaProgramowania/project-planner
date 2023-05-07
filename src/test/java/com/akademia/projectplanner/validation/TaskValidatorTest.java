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
  void shouldBeFalseWhenTaskHasNameNonBlank() {
    // given
    taskDto.setName("Task number 1");

    // when
    boolean result = TaskValidator.hasBlankName(taskDto);

    // then
    assertFalse(result);
  }

  @Test
  void shouldBeFalseWhenDeadlineYesterday() {
    // given
    LocalDate yesterday = LocalDate.now().minusDays(1);
    taskDto.setDeadline(yesterday.toString());

    // when
    boolean result = TaskValidator.isDeadlineValid(taskDto);

    // then
    assertFalse(result);
  }

  @Test
  void shouldBeTrueWhenDeadlineEmpty() {
    // given
    taskDto.setDeadline("");

    // when
    boolean result = TaskValidator.isDeadlineValid(taskDto);

    // then
    assertTrue(result);
  }

  @Test
  void shouldBeTrueWhenDeadlineTomorrow() {
    // given
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    taskDto.setDeadline(tomorrow.toString());

    // when
    boolean result = TaskValidator.isDeadlineValid(taskDto);

    // then
    assertTrue(result);
  }
}
