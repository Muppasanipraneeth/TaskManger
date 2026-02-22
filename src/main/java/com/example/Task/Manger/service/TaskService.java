package com.example.Task.Manger.service;

import com.example.Task.Manger.exception.ResourceNotFound;
import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import com.example.Task.Manger.repository.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class TaskService implements TaskMangerInterface {

    @Autowired
    private TaskManagerRepository taskManagerRepository;

    @Override
    public List<Task> getAllTask() {
         return taskManagerRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {

       return taskManagerRepository.save(task);
    }

    @Override
    public Task getTaskById(long id) {

        return taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task "+id+" not found"));
    }

    @Override
    public Task updateTask(Long id ,Task task) {
       taskManagerRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFound("Task " + id + " not found"));
       task.setId(id);

       return taskManagerRepository.save(task);
    }

    @Override
    public String deleteTaskById(long id) {
        taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task " + id + " not found"));

        taskManagerRepository.deleteById(id);
        return "task is deleted Successfully";
    }

    @Override
    public List<Task> getTaskByStatus(TaskStatus name) {

        return  taskManagerRepository.findByStatus(name);
    }
}
