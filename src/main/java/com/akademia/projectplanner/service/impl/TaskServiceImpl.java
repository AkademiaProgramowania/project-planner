package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.mapper.UserMapper;
import com.akademia.projectplanner.repository.UserRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

  private TaskMapper taskMapper;
  private TaskRepository taskRepository;
  private UserRepository userRepository;
  private UserMapper userMapper;

  public void addTask(TaskDto taskDto) {
    if (TaskValidator.hasBlankName(taskDto)) {
      throw new IllegalArgumentException("Mandatory fields are not filled in!");
    }
    if (!TaskValidator.isDeadlineValid(taskDto)) {
      throw new DateTimeException("Invalid date selected!");
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
            .orElseThrow(() -> new TaskDoesNotExistException("Task does not exist!"));

    return taskMapper.toTaskDto(foundTask);
  }

  public Map<TaskDto, String> createTasksEmailsMap(List<TaskDto> taskList) {
    Map<TaskDto, String> taskUserMap = new HashMap<>();
    for (TaskDto taskDto : taskList) {
      Long userId = taskDto.getUserId();
      UserEntity userEntity =
          userRepository
              .findById(userId)
              .orElseThrow(() -> new UserDoesNotExistException("User does not exist!"));
      String email = userEntity.getEmail();
      taskUserMap.put(taskDto, email);
    }
    return taskUserMap;
  }
}
