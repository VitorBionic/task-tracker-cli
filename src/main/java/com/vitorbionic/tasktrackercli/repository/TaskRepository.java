package com.vitorbionic.tasktrackercli.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vitorbionic.tasktrackercli.model.Task;

@Repository
public class TaskRepository {
    
    private static final String FILE_PATH = System.getProperty("user.home") + "/.task-cli/tasks.json";
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    
    {
        new File(FILE_PATH).getParentFile().mkdirs();
    }
    
    public List<Task> loadTasks() {
        try {
            File file = new File(FILE_PATH);
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Task[].class)));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
