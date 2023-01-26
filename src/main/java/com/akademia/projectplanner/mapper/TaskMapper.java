package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {
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

    taskEntity.setDeadline(taskDto.getDeadline());
    taskEntity.setName(taskDto.getName());
    taskEntity.setDescription(taskDto.getDescription());
    taskEntity.setStatus(taskDto.getStatus());
    taskEntity.setDeadline(taskDto.getDeadline());
    taskEntity.setStartDate(taskDto.getStartDate());
    taskEntity.setUser(taskDto.getUser());

    return taskEntity;
  }

  public TaskDto toTaskDto(TaskEntity taskEntity) {

    if (taskEntity == null) {
      throw new TaskDoesNotExistException("Task does not exist!");
    }

    TaskDto taskDto = new TaskDto();
    taskDto.setId(taskEntity.getId());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setName(taskEntity.getName());
    taskDto.setDescription(taskEntity.getDescription());
    taskDto.setStatus(taskEntity.getStatus());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setStartDate(taskEntity.getStartDate());
    taskDto.setUser(taskEntity.getUser());

    return taskDto;
  }
}
