package com.akademia.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a task entity that is stored in the TaskRepository. Contains all necessary fields and
 * methods to interact with a task: name, status, description, startDate, deadline and user
 * information.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tasks")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String status;
  private String description;
  private LocalDate startDate;
  private String deadline;

  @ManyToOne(fetch = FetchType.EAGER)
  private UserEntity user;

  /**
   * Creates a new task entity with the specified properties.
   *
   * @param name the name of the task
   * @param status the status of the task
   * @param description the description of the task
   * @param deadline the deadline of the task
   */
  public TaskEntity(String name, String status, String description, String deadline) {
    this.name = name;
    this.status = status;
    this.description = description;
    this.deadline = deadline;
    this.startDate = LocalDate.now();
  }
}
