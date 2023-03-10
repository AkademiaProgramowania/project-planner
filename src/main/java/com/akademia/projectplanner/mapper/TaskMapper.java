package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.entity.UserEntity;
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
      throw new TaskDoesNotExistException("Task does not exist!");
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
    taskEntity.setUser(getUser(taskDto.getUserId()));

    return taskEntity;
  }

  private UserEntity getUser(Long id) {

    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserDoesNotExistException("User does not exist!"));
  }

  public TaskDto toTaskDto(TaskEntity taskEntity) {

    if (taskEntity == null) {
      throw new TaskDoesNotExistException("Task does not exist!");
    }

    TaskDto taskDto = new TaskDto();
    taskDto.setId(taskEntity.getId());
    taskDto.setName(taskEntity.getName());
    taskDto.setDescription(taskEntity.getDescription());
    taskDto.setStatus(taskEntity.getStatus());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setStartDate(taskEntity.getStartDate());
    taskDto.setUserId(getUserId(taskEntity));

    return taskDto;
  }

  private Long getUserId(TaskEntity taskEntity) {
    if (taskEntity.getUser() == null) {
      throw new UserDoesNotExistException("User does not exist!");
    }
    return taskEntity.getUser().getId();
  }
}
