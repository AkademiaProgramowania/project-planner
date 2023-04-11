package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;

import com.akademia.projectplanner.service.RegistrationService;
import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DateTimeException;

@AllArgsConstructor
@Controller
public class TaskController {

  private TaskService taskService;
  private RegistrationService registrationService;

  @GetMapping("/edit/{taskId}")
  public String getTaskEditPage(@PathVariable("taskId") Long taskId, Model model) {
    addUsersAttribute(model);
    try {
      TaskDto taskDto = taskService.getTaskInfo(taskId);
      model.addAttribute("task", taskDto);
    } catch (TaskDoesNotExistException e) {
      model.addAttribute("noTaskMessage", e.getMessage());
      return "task";
    }
    return "task";
  }

  @GetMapping("/add-task")
  public String getAddTaskPage(Model model) {
    TaskDto taskDto = new TaskDto();
    model.addAttribute("task", taskDto);
    addUsersAttribute(model);
    return "task";
  }

  @PostMapping("/task")
  public String addOrUpdateTask(@ModelAttribute("task") TaskDto taskDto, Model model) {
    addUsersAttribute(model);
    try {
      taskService.addTask(taskDto);
    } catch (IllegalArgumentException e) {
      model.addAttribute("fieldsNotFilledMessage", e.getMessage());
      return "task";
    } catch (DateTimeException d) {
      model.addAttribute("invalidDateMessage", d.getMessage());
      return "task";
    }
    return "redirect:/";
  }

  private void addUsersAttribute(Model model) {
    model.addAttribute("users", registrationService.getAllUsers());
  }
}
