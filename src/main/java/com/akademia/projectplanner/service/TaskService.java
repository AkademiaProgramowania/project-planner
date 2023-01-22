package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.mapper.TaskMapper;
import com.akademia.projectplanner.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

  private TaskMapper taskMapper;
  private TaskRepository taskRepository;

  public void addTask(TaskDto taskDto) {
    if (checkMandatoryFields(taskDto.getName(), taskDto.getStatus())) {
      throw new IllegalArgumentException("Mandatory fields are not filled in!");
    }
    if (!checkIfDatesAreValid(taskDto.getDeadline())) {
      throw new DateTimeException("Invalid date selected!");
    }

    taskDto.setStartDate(LocalDate.now());
    TaskEntity taskEntity = taskMapper.toTaskEntity(taskDto);
    taskRepository.save(taskEntity);
  }

  private boolean checkMandatoryFields(String name, String status) {
    return name.isBlank() || status.isBlank();
  }

  private boolean checkIfDatesAreValid(String deadline) {
    return deadline.isBlank() || LocalDate.now().isBefore(LocalDate.parse(deadline));
  }

  public List<TaskDto> getAllTasks() {

    return taskRepository.findAll().stream()
        .map(taskEntity -> taskMapper.toTaskDto(taskEntity))
        .collect(Collectors.toList());
  }

  public TaskDto getTaskInfo(Long taskId) {
    Optional<TaskEntity> taskOptional = taskRepository.findById(taskId);
    if (!taskOptional.isPresent()) {
      throw new TaskDoesNotExistException("Task does not exist!");
    } else {
      TaskEntity taskEntity = taskOptional.get();
      return taskMapper.toTaskDto(taskEntity);
    }
  }
}
