package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.entity.TaskEntity;
import com.akademia.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String processAddingTask(@ModelAttribute("taskRequest") TaskDto taskDto, Model model) {
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
