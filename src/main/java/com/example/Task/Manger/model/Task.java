package com.example.Task.Manger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "task")
public class Task extends BaseTask {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Integer priority;

}
