package com.example.Task.Manger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "task")
public class Task extends BaseTask {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
  @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Integer priority;

}
