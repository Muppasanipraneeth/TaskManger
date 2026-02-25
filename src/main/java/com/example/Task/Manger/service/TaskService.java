package com.example.Task.Manger.service;

import com.example.Task.Manger.dto.TaskResponse;
import com.example.Task.Manger.exception.ResourceNotFound;
import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import com.example.Task.Manger.model.User;
import com.example.Task.Manger.repository.TaskManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class TaskService implements TaskMangerInterface {

    @Autowired
    private TaskManagerRepository taskManagerRepository;

    @Transactional
    @Override
    public List<TaskResponse> getAllTask(User user) {
        return taskManagerRepository.findAllActive(user).stream().map(TaskResponse::from).toList();
    }

    @Transactional
    @Override
    public TaskResponse addTask(Task task) {
        return TaskResponse.from(taskManagerRepository.save(task));
    }

    @Override
    public TaskResponse getTaskById(long id) {
        return TaskResponse.from(taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task "+id+" not found")));
    }

    @Transactional
    @Override
    public TaskResponse updateTask(Long id ,Task task) {
       taskManagerRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFound("Task " + id + " not found"));
       task.setId(id);
       task.setDelete(false);
       return TaskResponse.from(taskManagerRepository.save(task));
    }

    @Transactional
    @Override
    public String deleteTaskById(long id) {
       Task task= taskManagerRepository.findById(id).orElseThrow(()->new ResourceNotFound("Task " + id + " not found"));
       task.setDelete(true);
        taskManagerRepository.save(task);
        return "task is deleted Successfully";
    }

    @Override
    public List<TaskResponse> getTaskByStatus(TaskStatus name, User currentUser) {
        List<Task> tasks = taskManagerRepository.findAllActive(currentUser);
  return tasks.stream().map(TaskResponse::from).toList();
    }

    @Override
    public TaskResponse restoreTaskById(Long id) {
        Task task =taskManagerRepository.findInActiveById(id).orElseThrow(()->new ResourceNotFound("Task "+id+" not found"));
        task.setDelete(false);


        return TaskResponse.from(taskManagerRepository.save(task)) ;
    }
}
