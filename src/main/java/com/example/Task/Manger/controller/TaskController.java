package com.example.Task.Manger.controller;

import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.service.TaskMangerInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskMangerInterface taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) TaskStatus taskStatus){
        List<Task> tasks;
        if(taskStatus != null){
            tasks = taskService.getTaskByStatus(taskStatus);
        }else{
            tasks = taskService.getAllTask();
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id){
        Task task= taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){

        return  new ResponseEntity<>( taskService.addTask(task),HttpStatus.CREATED);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable long id){
        String response=taskService.deleteTaskById(id);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@Valid @PathVariable long id,@RequestBody Task task){

        return  new ResponseEntity<>(taskService.updateTask(id,task),HttpStatus.OK);

    }
}
