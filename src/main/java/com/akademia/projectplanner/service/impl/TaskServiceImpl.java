package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.enums.ExceptionMessage;
import com.akademia.projectplanner.service.TaskService;
import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.mapper.TaskMapper;
import com.akademia.projectplanner.repository.TaskRepository;
import com.akademia.projectplanner.validation.TaskValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

  private TaskMapper taskMapper;
  private TaskRepository taskRepository;

  public void addTask(TaskDto taskDto) {
    if (TaskValidator.hasBlankName(taskDto)) {
      throw new IllegalArgumentException(ExceptionMessage.FIELDS_NOT_FILLED.getExceptionText());
    }
    if (!TaskValidator.isDeadlineValid(taskDto)) {
      throw new DateTimeException(ExceptionMessage.INVALID_DATE.getExceptionText());
    }

    TaskEntity taskEntity = taskMapper.toTaskEntity(taskDto);
    taskRepository.save(taskEntity);
  }

  public List<TaskDto> getAllTasks() {

    return taskRepository.findAll().stream()
        .map(taskEntity -> taskMapper.toTaskDto(taskEntity))
        .collect(Collectors.toList());
  }

  public TaskDto getTaskInfo(Long taskId) {
    TaskEntity foundTask =
        taskRepository
            .findById(taskId)
            .orElseThrow(
                () ->
                    new TaskDoesNotExistException(
                        ExceptionMessage.TASK_DOES_NOT_EXIST.getExceptionText()));

    return taskMapper.toTaskDto(foundTask);
  }
}
