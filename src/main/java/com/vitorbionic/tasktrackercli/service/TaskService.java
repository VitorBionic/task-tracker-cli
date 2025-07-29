package com.vitorbionic.tasktrackercli.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public void addTask(String description) {
        Task task = new Task(nextId, description, TaskStatus.TO_DO, LocalDateTime.now(), null);
        tasks.add(task);
        taskRepository.saveTasks(tasks);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }
    
    public void updateTask(Long id, String description) {
        Task task = findTask(id);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.saveTasks(tasks);
    }
    
    private Task findTask(Long id) {
        return tasks.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " does not exist!"));
    }
    
    public void deleteTask(Long id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                taskRepository.saveTasks(tasks);
                return;
            }
        }
        throw new IllegalArgumentException("Task with id " + id + " does not exist!");
    }
    
    public void markAsInProgress(Long id) {
        Task task = findTask(id);
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.saveTasks(tasks);
    }
    
    public void markAsDone(Long id) {
        Task task = findTask(id);
        task.setStatus(TaskStatus.DONE);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.saveTasks(tasks);
    }
    
    public void listAll() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    
    public void listAllByStatus(TaskStatus status) {
        List<Task> filteredTasks = tasks.stream()
                .filter(x -> x.getStatus() == status)
                .collect(Collectors.toList());
        
        for (Task task : filteredTasks) {
            System.out.println(task);
        }
    }
}
