package com.example.Task.Manger.repository;

import com.example.Task.Manger.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagerRepository extends JpaRepository<Task,Long> {

}
