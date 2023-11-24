package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.enums.Status;
import com.akademia.projectplanner.mapper.TaskMapper;
import com.akademia.projectplanner.repository.TaskRepository;
import com.akademia.projectplanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TaskServiceImplTest {
  @Mock private TaskMapper taskMapper;
  @Mock private TaskRepository taskRepository;
  @Mock private UserRepository userRepository;
  @InjectMocks TaskServiceImpl taskService;
  private TaskDto taskDto;
  private AutoCloseable autoCloseable;

  @BeforeEach
  void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
    taskDto = buildTaskDto();
  }

  @Test
  void shouldAddTaskSuccessfully() {
    // given
    TaskEntity taskEntity = new TaskEntity();
    Mockito.when(taskMapper.toTaskEntity(taskDto)).thenReturn(taskEntity);
    // when
    taskService.addTask(taskDto);
    // then
    verify(taskRepository, times(1)).save(taskEntity);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenTaskWithBlankName() throws IllegalArgumentException {
    // given
    taskDto.setName("");
    TaskEntity taskEntity = new TaskEntity();
    Mockito.when(taskMapper.toTaskEntity(taskDto)).thenReturn(taskEntity);

    // when & then
    assertThrows(IllegalArgumentException.class, () -> taskService.addTask(taskDto));
  }

  @Test
  void shouldThrowDateTimeExceptionWhenTaskWithInvalidDate() throws DateTimeException {
    // given
    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);
    taskDto.setDeadline(yesterday.toString());
    TaskEntity taskEntity = new TaskEntity();
    Mockito.when(taskMapper.toTaskEntity(taskDto)).thenReturn(taskEntity);

    // when & then
    assertThrows(DateTimeException.class, () -> taskService.addTask(taskDto));
  }

  @Test
  void getAllTasks() {
    // given
    List<TaskEntity> taskEntities = new ArrayList<>();
    taskEntities.add(new TaskEntity());

    Mockito.when(taskRepository.findAll()).thenReturn(taskEntities);
    Mockito.when(taskMapper.toTaskDto(Mockito.any(TaskEntity.class))).thenReturn(taskDto);

    // When
    List<TaskDto> taskDtos = taskService.getAllTasks();

    // Then
    assertNotNull(taskDtos);
    assertEquals(taskEntities.size(), taskDtos.size());
  }

  @Test
  void getTaskInfo() {}

  @Test
  void createTasksEmailsMap() {}

  private TaskDto buildTaskDto() {
    return new TaskDtoBuilder()
        .setId(1L)
        .setName("Name")
        .setStatus(Status.DONE)
        .setDescription("Description")
        .setDeadline("")
        .setStartDate(LocalDate.now())
        .setUserId(2L)
        .build();
  }
}
