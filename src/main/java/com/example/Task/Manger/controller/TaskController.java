package com.example.Task.Manger.controller;

import com.example.Task.Manger.dto.TaskResponse;
import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import com.example.Task.Manger.service.TaskMangerInterface;
import com.example.Task.Manger.model.User;
import com.example.Task.Manger.repository.UserRepository;
import com.example.Task.Manger.service.TaskMangerInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskMangerInterface taskService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('READ_TASK')")
    public ResponseEntity<List<TaskResponse>> getAllTask(@RequestParam(required = false) TaskStatus taskStatus){
        List<TaskResponse> tasks;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        if(taskStatus != null){
            tasks = taskService.getTaskByStatus(taskStatus,user);
        }else{
            tasks = taskService.getAllTask(user);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }
    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('READ_TASK')")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long id){
        TaskResponse task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @PostMapping("/tasks")
    @PreAuthorize("hasAuthority('CREATE_TASK')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody Task task){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        task.setUser(user);
        return  new ResponseEntity<>(taskService.addTask(task),HttpStatus.CREATED);
    }
    @DeleteMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('DELETE_TASK')")
    public ResponseEntity<String> deleteTaskById(@PathVariable long id){
        String response=taskService.deleteTaskById(id);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('UPDATE_TASK')")
    public ResponseEntity<TaskResponse> updateTask(@Valid @PathVariable long id,@RequestBody Task task){
        return  new ResponseEntity<>(taskService.updateTask(id,task),HttpStatus.OK);
    }
    @PostMapping("/tasks/restore/{id}")
    @PreAuthorize("hasAuthority('UPDATE_TASK')")
    public ResponseEntity<TaskResponse> restoreTask(@Valid @PathVariable long id){
        return  new ResponseEntity<>(taskService.restoreTaskById(id), HttpStatus.OK);
    }

}
