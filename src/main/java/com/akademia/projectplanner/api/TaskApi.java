package com.akademia.projectplanner.api;

import com.akademia.projectplanner.dto.TaskDto;

import java.util.List;

public interface TaskApi {

    void addTask(TaskDto taskDto);
    List<TaskDto> getAllTasks();
    TaskDto getTaskInfo(Long taskId);
}
