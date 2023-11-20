package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.enums.Status;
import com.akademia.projectplanner.mapper.TaskMapper;
import com.akademia.projectplanner.repository.TaskRepository;
import com.akademia.projectplanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;


class TaskServiceImplTest {
    @Mock private TaskMapper taskMapper;
    @Mock private TaskRepository taskRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks TaskServiceImpl taskService;
    private TaskDto taskDto;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        taskDto = buildTaskDto();
    }

    @Test
    void addTask() {
    }

    @Test
    void getAllTasks() {
    }

    @Test
    void getTaskInfo() {
    }

    @Test
    void createTasksEmailsMap() {
    }

    private TaskDto buildTaskDto() {
        return new TaskDtoBuilder()
                .setId(1L)
                .setName("Name")
                .setStatus(Status.DONE)
                .setDescription("Description")
                .setDeadline("")
                .setStartDate(LocalDate.now())
                .setUserId(2L)
                .build();
    }
}