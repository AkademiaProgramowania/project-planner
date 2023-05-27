package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.service.TaskService;
import com.akademia.projectplanner.dto.TaskDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Controller class that handles requests for the home page of the application. The rendered view of
 * this controller is displayed to users after a successful login and provides links to other parts
 * of the application.
 */
@AllArgsConstructor
@Controller
public class HomeController {

  private TaskService taskService;

  /**
   * Handles GET requests for the main page of the application. The method retrieves a list of all
   * tasks from the taskService and adds it to the model object.
   *
   * @param model the model object that is used to render the view
   * @return the name of the view (index.html) to be rendered by the Spring MVC framework
   */
  @GetMapping({"/", "index"})
  public String getMainPage(Model model) {

    List<TaskDto> allTasks = taskService.getAllTasks();
    Map<TaskDto, UserDto> taskUserMap = taskService.getTaskUserMap(allTasks);
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("userName", userDetails.getUsername());
    model.addAttribute("taskUserMap", taskUserMap);

    return "index";
  }
}
