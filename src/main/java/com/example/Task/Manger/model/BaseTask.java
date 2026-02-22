package com.example.Task.Manger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseTask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(updatable = false, nullable = false)
    private LocalDateTime addedDate;
    private LocalDateTime updatedDate;
    @Column(name = "`delete`")
    private boolean delete;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.delete=false;
        this.addedDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }


}
