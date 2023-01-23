package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;

import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class TaskController {

  private TaskService taskService;

  @GetMapping("/task-details/{taskId}")
  public String getTaskDetailsPage(@PathVariable("taskId") Long taskId, Model model) {
    try {
      TaskDto taskDto = taskService.getTaskInfo(taskId);
      model.addAttribute("taskToView", taskDto);
    } catch (TaskDoesNotExistException e) {
      model.addAttribute("noTaskMessage", e.getMessage());
      return "task-details";
    }
    return "task-details";
  }
}
