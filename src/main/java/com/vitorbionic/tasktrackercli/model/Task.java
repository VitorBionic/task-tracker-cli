package com.vitorbionic.tasktrackercli.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    
    private Long id;
    
    private String description;
    
    private TaskStatus status;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    public Task() {}
    
    public Task(Long id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String created = createdAt != null ? createdAt.format(fmt) : "-";
        String updated = updatedAt != null ? updatedAt.format(fmt) : "-";
        return String.format("Task %d: %s | %s | created: %s | updated: %s",
                id, description, status, created, updated);
    }
    
}
