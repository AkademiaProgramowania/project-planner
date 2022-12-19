package com.akademia.projectplanner.repository;

import com.akademia.projectplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByName(String name);
}
