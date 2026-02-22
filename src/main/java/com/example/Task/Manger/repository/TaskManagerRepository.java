package com.example.Task.Manger.repository;

import com.example.Task.Manger.model.Task;
import com.example.Task.Manger.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TaskManagerRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.delete=false AND t.status=:status")
    public List<Task> findByStatus(@Param("status") TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.delete=false")
    public List<Task> findAllActive();

    @Query("SELECT t FROM Task t WHERE t.delete=true")
    public List<Task> findAllDeleted();

    @Query("SELECT t FROM Task t WHERE t.id=:id AND t.delete=false")
    public Optional<Task> findById(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.id=:id AND t.delete=true")
    public Optional<Task> findInActiveById(@Param("id") Long id);



}
