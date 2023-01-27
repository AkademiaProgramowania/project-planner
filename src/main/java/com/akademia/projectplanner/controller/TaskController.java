package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;

import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.DateTimeException;

@AllArgsConstructor
@Controller
public class TaskController {

  private TaskService taskService;

  @GetMapping("/edit/{taskId}")
  public String getTaskEditPage(@PathVariable("taskId") Long taskId, Model model) {
    try {
      TaskDto taskDto = taskService.getTaskInfo(taskId);
      model.addAttribute("taskToEdit", taskDto);
    } catch (TaskDoesNotExistException e) {
      model.addAttribute("noTaskMessage", e.getMessage());
      return "edit-task";
    }
    return "edit-task";
  }

  @PostMapping("/edit")
  public String editTask(
      @Valid @ModelAttribute("taskToEdit") TaskDto taskDto,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "edit-task";
    }
    try {
      taskService.addTask(taskDto);
    } catch (IllegalArgumentException e) {
      model.addAttribute("fieldsNotFilledMessage", e.getMessage());
      return "edit-task";
    } catch (DateTimeException d) {
      model.addAttribute("invalidDateMessage", d.getMessage());
      return "edit-task";
    }
    return "redirect:/";
  }
}
