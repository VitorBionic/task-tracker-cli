package com.vitorbionic.tasktrackercli.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitorbionic.tasktrackercli.model.Task;
import com.vitorbionic.tasktrackercli.model.TaskStatus;
import com.vitorbionic.tasktrackercli.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    
    private List<Task> tasks;
    private Long nextId;
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.tasks = taskRepository.loadTasks();
        this.nextId = tasks.stream()
                .mapToLong(Task::getId)
                .max()
                .orElse(0L) + 1;
    }
    
    public Long addTask(String description) {
        Task task = new Task(nextId, description, TaskStatus.TO_DO, LocalDateTime.now(), null);
        taskRepository.saveTasks(tasks);
        return nextId;
    }
    
    public void updateTask(Long id, String description) {
        Task task = tasks.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " does not exist!"));
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.saveTasks(tasks);
    }
    
    public void deleteTask(Long id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                taskRepository.saveTasks(tasks);
                return;
            }
        }
        throw new IllegalArgumentException("Task with id " + id + " does not exist!");
    }
    
    
}
