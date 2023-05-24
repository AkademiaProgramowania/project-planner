package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.Status;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

  private TaskDto taskDto;
  private TaskEntity taskEntity;

  @Mock private UserRepository userRepository;
  @InjectMocks private TaskMapper taskMapper;
  private AutoCloseable autoCloseable;

  @BeforeEach
  void setUp() {
    taskDto = new TaskDto();
    taskEntity = new TaskEntity();
    autoCloseable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void cleanup() throws Exception {
    autoCloseable.close();
  }

  @Test
  void shouldMapTaskDtoToTaskEntitySuccessfully() {
    // given
    Long userId = 1L;
    taskDto.setId(1L);
    taskDto.setUserId(userId);
    taskDto.setName("Name");
    taskDto.setDeadline(null);
    taskDto.setDescription("Description");
    taskDto.setStatus(Status.DONE);
    taskDto.setStartDate(LocalDate.now());

    UserEntity mockUser = new UserEntity();
    mockUser.setId(userId);
    Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    // when
    TaskEntity taskEntityMapped = taskMapper.toTaskEntity(taskDto);

    // then

    assertEquals(taskDto.getName(), taskEntityMapped.getName());
    assertEquals(taskDto.getId(), taskEntityMapped.getId());
    assertEquals(taskDto.getDescription(), taskEntityMapped.getDescription());
    assertEquals(taskDto.getStatus(), taskEntityMapped.getStatus());
    assertEquals(taskDto.getDeadline(), taskEntityMapped.getDeadline());
    assertEquals(taskDto.getStartDate(), taskEntityMapped.getStartDate());
  }

  @Test
  void shouldThrowUserExceptionWhenTaskDtoUserNull() {
    // given
    taskDto.setUserId(null);

    // when & then
    assertThrows(UserDoesNotExistException.class, () -> taskMapper.toTaskEntity(taskDto));
  }

  @Test
  void shouldThrowExceptionForNullTaskDto() {
    // when & then
    assertThrows(TaskDoesNotExistException.class, () -> taskMapper.toTaskEntity(null));
  }

  @Test
  void shouldMapTaskEntityToTaskDtoSuccessfully() {
    // given
    taskEntity.setId(1L);
    taskEntity.setName("Name");
    taskEntity.setUser(new UserEntity());
    taskEntity.setDescription("Description");
    taskEntity.setStatus(Status.TO_DO);
    taskEntity.setDeadline(null);
    taskEntity.setStartDate(LocalDate.now());

    // when
    TaskDto taskDtoMapped = taskMapper.toTaskDto(taskEntity);

    // then
    assertEquals(taskEntity.getName(), taskDtoMapped.getName());
    assertEquals(taskEntity.getId(), taskDtoMapped.getId());
    assertEquals(taskEntity.getDescription(), taskDtoMapped.getDescription());
    assertEquals(taskEntity.getStatus(), taskDtoMapped.getStatus());
    assertEquals(taskEntity.getDeadline(), taskDtoMapped.getDeadline());
    assertEquals(taskEntity.getStartDate(), taskDtoMapped.getStartDate());
    assertEquals(taskEntity.getUser().getId(), taskDtoMapped.getUserId());
  }

  @Test
  void shouldThrowUserExceptionWhenTaskEntityUserNull() {
    // given
    taskEntity.setUser(null);

    // when & then
    assertThrows(UserDoesNotExistException.class, () -> taskMapper.toTaskDto(taskEntity));
  }

  @Test
  void shouldThrowExceptionForNullTaskEntity() {
    // when & then
    assertThrows(TaskDoesNotExistException.class, () -> taskMapper.toTaskDto(null));
  }
}
