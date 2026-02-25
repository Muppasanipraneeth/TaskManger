package com.example.Task.Manger.service;

import com.example.Task.Manger.dto.TaskResponse;
import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import com.example.Task.Manger.model.User;


import java.util.List;

public interface TaskMangerInterface {
    public List<TaskResponse> getAllTask(User user);
    public TaskResponse addTask(Task task);
    public TaskResponse getTaskById(long id);
    public TaskResponse updateTask(Long id, Task task);
    public String deleteTaskById(long id);
    public TaskResponse restoreTaskById(Long id);
    public List<TaskResponse> getTaskByStatus(TaskStatus taskStatus, User user);
}
