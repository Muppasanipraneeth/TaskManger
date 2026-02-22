package com.example.Task.Manger.service;

import com.example.Task.Manger.exception.ResourceNotFound;
import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.repository.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class TaskService implements TaskMangerInterface {

    @Autowired
    private TaskManagerRepository taskManagerRepository;

    @Override
    public Iterable<Task> getAllTask() {
         return taskManagerRepository.findAll();
    }

    @Override
    public String addTask(Task task) {

       taskManagerRepository.save(task);
       return "task is added Successfully";
    }

    @Override
    public Task getTaskById(long id) {

        return taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task "+id+" not found"));
    }

    @Override
    public String updateTask(Long id ,Task task) {
       taskManagerRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFound("Task " + id + " not found"));
       task.setId(id);
       taskManagerRepository.save(task);
       return "task is updated Successfully";
    }

    @Override
    public String deleteTaskById(long id) {
        taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task " + id + " not found"));

        taskManagerRepository.deleteById(id);
        return "task is deleted Successfully";
    }
}
