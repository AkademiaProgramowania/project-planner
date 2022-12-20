package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.exception.TaskDoesNotExistException;
import com.akademia.projectplanner.entity.Task;
import com.akademia.projectplanner.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public void addTask(TaskDto taskDto) {
        if (checkMandatoryFields(taskDto.getName(), taskDto.getStatus())) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (!checkIfDatesAreValid(taskDto.getDeadline())) {
            throw new DateTimeException("Invalid date selected!");
        }
        Task task = new Task(taskDto.getName(), taskDto.getStatus(), taskDto.getDescription(), taskDto.getDeadline());
        taskRepository.save(task);
    }

    private boolean checkMandatoryFields(String name, String status) {
        return name.isBlank() || status.isBlank();
    }

    private boolean checkIfDatesAreValid(String deadline) {
        return deadline.isBlank() || LocalDate.now().isBefore(LocalDate.parse(deadline));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskInfo(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElseThrow(() -> new TaskDoesNotExistException("Task does not exist!"));
    }
}
