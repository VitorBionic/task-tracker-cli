package com.vitorbionic.tasktrackercli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vitorbionic.tasktrackercli.repository.TaskRepository;

public class TaskServiceTest {

    private TaskService service;
    private TaskRepository mockRepo;

    @BeforeEach
    void setUp() {
        mockRepo = mock(TaskRepository.class);
        when(mockRepo.loadTasks()).thenReturn(new java.util.ArrayList<>());
        service = new TaskService(mockRepo);
    }

    @Test
    void testAddTask() {
        service.addTask("Learn JUnit");
        verify(mockRepo, times(1)).saveTasks(any());
    }

    @Test
    void testDeleteNonExistentTaskThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> service.deleteTask(99L));
        assertEquals("Task with id 99 does not exist!", ex.getMessage());
    }
}

