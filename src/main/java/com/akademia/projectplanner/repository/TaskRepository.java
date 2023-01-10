package com.akademia.projectplanner.repository;

import com.akademia.projectplanner.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
