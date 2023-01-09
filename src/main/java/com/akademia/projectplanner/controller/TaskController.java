package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.entity.TaskEntity;
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

    @GetMapping("/task-details/{taskId}")
    public String getTaskDetailsPage(@PathVariable("taskId") Long taskId, Model model) {
        try {
            TaskEntity taskEntity = taskService.getTaskInfo(taskId);
            model.addAttribute("taskToView", taskEntity);
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
            return "task-details";
        }
        return "task-details";
    }
}
