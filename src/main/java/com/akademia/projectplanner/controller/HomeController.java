package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.DateTimeException;
import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

  private TaskService taskService;

  @GetMapping("/")
  public String getMainPage(Model model) {
    List<TaskEntity> taskEntities = taskService.getAllTasks();
    model.addAttribute("tasks", taskEntities);

    getTaskRequest(model);
    return "index";
  }

  public String getTaskRequest(Model model) {
    TaskDto taskDto = new TaskDto();
    model.addAttribute("taskRequest", taskDto);
    return "index";
  }

  @PostMapping("/")
  public String processAddingTask(
      @Valid @ModelAttribute("taskRequest") TaskDto taskDto,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "index";
    }
    try {
      taskService.addTask(taskDto);
    } catch (IllegalArgumentException e) {
      model.addAttribute("fieldsNotFilledMessage", e.getMessage());
      return "index";
    } catch (DateTimeException d) {
      model.addAttribute("invalidDateMessage", d.getMessage());
      return "index";
    }
    return "redirect:/";
  }
}
