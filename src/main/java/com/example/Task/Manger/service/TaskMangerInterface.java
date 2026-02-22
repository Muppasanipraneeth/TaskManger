package com.example.Task.Manger.service;

import com.example.Task.Manger.model.Task;


public interface TaskMangerInterface {
    public Iterable<Task> getAllTask();
    public String addTask(Task task);
    public Task getTaskById(long id);
    public String updateTask(Long id, Task task);
    public String deleteTaskById(long id);
}
