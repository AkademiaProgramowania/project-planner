package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.service.RegistrationService;
import com.akademia.projectplanner.service.TaskService;
import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DateTimeException;

/**
 * The TaskController handles requests related to displaying the add task form, task creation and
 * its edition.
 */
@AllArgsConstructor
@Controller
public class TaskController {

  private TaskService taskService;
  private RegistrationService registrationService;

  /**
   * Handles GET request for the page with form allowing to edit a task with specific ID. Creates a
   * new TaskDto object and adds it to the model. Adds all existing users to the view so that task
   * can be assigned to one of them.
   *
   * @param taskId the ID of the task to be edited
   * @param model the Spring Model object that contains attributes to be passed to the view
   * @return the name of the view (task.html), otherwise returns the task form with an error message
   */
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

  /**
   * Handles GET request for the page with form allowing to add a task with specific ID. Creates a
   * new TaskDto object and adds it to the model. Adds all existing users to the view so that the
   * task can be assigned to one of them.
   *
   * @param model the Spring Model object that contains attributes to be passed to the view
   * @return the name of the view (task.html) to be rendered by the Spring MVC framework
   */
  @GetMapping("/add-task")
  public String getAddTaskPage(Model model) {
    TaskDto taskDto = new TaskDto();
    model.addAttribute("task", taskDto);
    addUsersAttribute(model);
    return "task";
  }

  /**
   * Handles POST request allowing to create a new task or edit a task with specific ID. Adds the
   * list of users to the model so that the task can be assigned to one of them.
   *
   * @param taskDto The task object to add or update
   * @param model The model object to add attributes to
   * @return redirect to the home page if successful, otherwise returns the task form with an error
   *     message
   */
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

  /**
   * Adds all existing users to the edit task form view so that the task can be assigned to one of
   * them.
   *
   * @param model the Spring Model object that contains attributes to be passed to the view
   */
  private void addUsersAttribute(Model model) {
    model.addAttribute("users", registrationService.getAllUsers());
  }
}
