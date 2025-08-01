package com.vitorbionic.tasktrackercli.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vitorbionic.tasktrackercli.model.Task;
import com.vitorbionic.tasktrackercli.model.TaskStatus;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repo;
    
    @BeforeAll
    static void setupFile() {
        File file = new File(System.getProperty("java.io.tmpdir"), "tasks-test.json");
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    
    @Test
    void testSaveAndLoadTasks() {
        Task task = new Task(1L, "JUnit Test", TaskStatus.TO_DO, LocalDateTime.now(), null);
        repo.saveTasks(List.of(task));

        List<Task> tasks = repo.loadTasks();
        assertEquals(1, tasks.size());
        assertEquals("JUnit Test", tasks.get(0).getDescription());
    }
}
