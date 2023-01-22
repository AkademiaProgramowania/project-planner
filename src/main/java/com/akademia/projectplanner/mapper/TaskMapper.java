package com.akademia.projectplanner.mapper;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
  public TaskEntity toTaskEntity(TaskDto taskDto) {

    if (taskDto == null) {
      return null;
    }
    TaskEntity taskEntity = new TaskEntity();

    taskEntity.setId(taskDto.getId());
    taskEntity.setDeadline(taskDto.getDeadline());
    taskEntity.setName(taskDto.getName());
    taskEntity.setDescription(taskDto.getDescription());
    taskEntity.setStatus(taskDto.getStatus());
    taskEntity.setDeadline(taskDto.getDeadline());
    taskEntity.setStartDate(taskDto.getStartDate());
    taskEntity.setUser(taskDto.getUserEntity());
    return taskEntity;
  }

  public TaskDto toTaskDto(TaskEntity taskEntity) {

    if (taskEntity == null) {
      return null;
    }
    TaskDto taskDto = new TaskDto();
    taskDto.setId(taskEntity.getId());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setName(taskEntity.getName());
    taskDto.setDescription(taskEntity.getDescription());
    taskDto.setStatus(taskEntity.getStatus());
    taskDto.setDeadline(taskEntity.getDeadline());
    taskDto.setStartDate(taskEntity.getStartDate());
    taskDto.setUserEntity(taskEntity.getUser());
    return taskDto;
  }
}
