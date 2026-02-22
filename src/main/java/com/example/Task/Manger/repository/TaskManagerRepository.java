package com.example.Task.Manger.repository;

import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskManagerRepository extends JpaRepository<Task,Long> {
    public List<Task> findByStatus(TaskStatus taskStatus);

}
