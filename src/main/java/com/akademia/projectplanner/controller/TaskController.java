package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.Dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.model.Task;
import com.akademia.projectplanner.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.DateTimeException;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/add-task")
    public String getAddTaskPage(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("taskRequest", taskDto);
        return "task";
    }

    @PostMapping("/add-task")
    public String processAddingTask(@ModelAttribute("taskRequest") TaskDto taskDto, Model model) {
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

    @GetMapping("/task-details/{taskId}")
    public String getTaskDetailsPage(@PathVariable("taskId") Long taskId, Model model) {
        try {
            Task task = taskService.getTaskInfo(taskId);
            model.addAttribute("taskToView", task);
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
            return "task-details";
        }
        return "task-details";
    }
}
