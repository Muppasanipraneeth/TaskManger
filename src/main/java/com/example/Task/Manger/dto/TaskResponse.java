package com.example.Task.Manger.dto;

import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    Integer priority;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long userId;
    String userName;

    public TaskResponse(Long id, String title, String description, TaskStatus taskStatus, Integer priority, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, String userName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.userName = userName;
    }
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getAddedDate(),
                task.getUpdatedDate(),
                task.getUser().getId(),
                task.getUser().getUsername()
        );
    }

}
