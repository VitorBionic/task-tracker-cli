package com.vitorbionic.tasktrackercli.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vitorbionic.tasktrackercli.model.Task;

@Repository
public class TaskRepository {
    
    private final ObjectMapper objectMapper;
    private final String filePath;
    
    public TaskRepository(@Value("${task.file-path}") String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        new File(filePath).getParentFile().mkdirs();
    }
    
    public List<Task> loadTasks() {
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Task[].class)));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
