package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.entity.TaskEntity;
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

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<TaskEntity> taskEntities = taskService.getAllTasks();
        model.addAttribute("tasks", taskEntities);
        return "index";
    }
}
