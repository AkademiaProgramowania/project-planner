package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

  private TaskService taskService;

  @GetMapping({"/", "index"})
  public String getMainPage(Model model) {
    List<TaskDto> allTasks = taskService.getAllTasks();

    model.addAttribute("tasks", allTasks);

    return "index";
  }
}
