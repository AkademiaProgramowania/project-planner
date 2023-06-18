package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.ExceptionMessage;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class TaskMapper {
  private UserRepository userRepository;

  public TaskEntity toTaskEntity(TaskDto taskDto) {

    if (taskDto == null) {
      throw new TaskDoesNotExistException(ExceptionMessage.TASK_DOES_NOT_EXIST.getExceptionText());
    }

    TaskEntity taskEntity = new TaskEntity();

    if (taskDto.getStartDate() == null) {
      taskDto.setStartDate(LocalDate.now());
    }

    if (taskDto.getId() != null) {
      taskEntity.setId(taskDto.getId());
    }

    taskEntity.setName(taskDto.getName());
    taskEntity.setDescription(taskDto.getDescription());
    taskEntity.setStatus(taskDto.getStatus());
    taskEntity.setDeadline(taskDto.getDeadline());
    taskEntity.setStartDate(taskDto.getStartDate());
    if (taskDto.getUserId() != null) {
      taskEntity.setUser(getUser(taskDto.getUserId()));
    }

    return taskEntity;
  }

  private UserEntity getUser(Long id) {

    return userRepository
        .findById(id)
        .orElseThrow(
            () ->
                new UserDoesNotExistException(
                    ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText()));
  }

  public TaskDto toTaskDto(TaskEntity taskEntity) {

    if (taskEntity == null) {
      throw new TaskDoesNotExistException(ExceptionMessage.TASK_DOES_NOT_EXIST.getExceptionText());
    }

    TaskDto taskDto = new TaskDto();
    taskDto.setId(taskEntity.getId());
    taskDto.setName(taskEntity.getName());
    taskDto.setDescription(taskEntity.getDescription());
    taskDto.setStatus(taskEntity.getStatus());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setStartDate(taskEntity.getStartDate());
    if (taskEntity.getUser() != null) {
      taskDto.setUserId(getUserId(taskEntity));
    }

    return taskDto;
  }

  private Long getUserId(TaskEntity taskEntity) {
    return taskEntity.getUser().getId();
  }
}
