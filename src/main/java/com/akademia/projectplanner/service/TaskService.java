package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.TaskDto;

import java.util.List;
import java.util.Map;

/** The interface used to communication with TaskServiceImpl. */
public interface TaskService {

  /**
   * Saves newly created user in the database. Method throws IllegalArgumentException if mandatory
   * fields are not filled in, or DateTimeException if invalid date is selected.
   *
   * @param taskDto The task object to add.
   */
  void addTask(TaskDto taskDto);

  /**
   * Finds all tasks in database and maps TaskEntity to TaskDto.
   *
   * @return The list of TaskDto.
   */
  List<TaskDto> getAllTasks();

  /**
   * Finds particular task by id and maps TaskEntity to TaskDto. It throws TaskDoesNotExistException
   * if task is not found.
   *
   * @param taskId the ID of the task to be found.
   * @return found TaskDto.
   */
  TaskDto getTaskInfo(Long taskId);

  /**
   * Creates a map based on task list and the associated users.
   *
   * @param taskList the list of created tasks Dto.
   * @return a map containing existing task and its corresponding user.
   */
  Map<TaskDto, String> createTasksEmailsMap(List<TaskDto> taskList);
}
