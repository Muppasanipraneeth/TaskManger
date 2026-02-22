package com.example.Task.Manger.service;

import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;


import java.util.List;

public interface TaskMangerInterface {
    public List<Task> getAllTask();
    public Task addTask(Task task);
    public Task getTaskById(long id);
    public Task updateTask(Long id, Task task);
    public String deleteTaskById(long id);
    public List<Task> getTaskByStatus(TaskStatus name);
}
