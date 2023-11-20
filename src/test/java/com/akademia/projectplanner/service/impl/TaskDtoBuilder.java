package com.akademia.projectplanner.service.impl;

import com.akademia.projectplanner.dto.TaskDto;
import com.akademia.projectplanner.enums.Status;

import java.time.LocalDate;

public class TaskDtoBuilder {
    private Long id;
    private String name;
    private Status status;
    private String description;
    private String deadline;
    private LocalDate startDate;
    private Long userId;


    public TaskDtoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TaskDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TaskDtoBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TaskDtoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskDtoBuilder setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public TaskDtoBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public TaskDtoBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TaskDto build() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(this.id);
        taskDto.setName(this.name);
        taskDto.setStatus(this.status);
        taskDto.setDescription(this.description);
        taskDto.setDeadline(this.deadline);
        taskDto.setStartDate(this.startDate);
        taskDto.setUserId(this.userId);
        return taskDto;
    }
}
