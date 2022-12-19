package com.akademia.projectplanner.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String description;
    private LocalDate startDate;
    private String deadline;


    public Task(String name, String status, String description, String deadline) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.deadline = deadline;
        this.startDate = LocalDate.now();
    }
}
