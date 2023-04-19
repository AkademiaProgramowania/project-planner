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
  void testTaskNonBlank() {
    taskDto.setStatus("In progress");
    taskDto.setName("Task number 1");
    assertFalse(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void testTaskBlankNameAndStatus() {
    taskDto.setStatus("");
    taskDto.setName("");
    assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void testTaskBlankStatusNonBlankName() {
    taskDto.setStatus("");
    taskDto.setName("Test task");
    assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void testTaskBlankNameNonBlankStatus() {
    taskDto.setStatus("In progress");
    taskDto.setName("");
    assertTrue(TaskValidator.hasBlankNameOrStatus(taskDto));
  }

  @Test
  void testIsDeadlineYesterdayValid() {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    taskDto.setDeadline(yesterday.toString());
    assertFalse(TaskValidator.isDeadlineValid(taskDto));
  }

  @Test
  void testIsDeadlineEmptyValid() {
    taskDto.setDeadline("");
    assertTrue(TaskValidator.isDeadlineValid(taskDto));
  }

  @Test
  void testIsDeadlineTomorrowValid() {
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    taskDto.setDeadline(tomorrow.toString());
    assertTrue(TaskValidator.isDeadlineValid(taskDto));
  }
}
