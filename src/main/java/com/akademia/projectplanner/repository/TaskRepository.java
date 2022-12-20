package com.akademia.projectplanner.repository;

import com.akademia.projectplanner.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
