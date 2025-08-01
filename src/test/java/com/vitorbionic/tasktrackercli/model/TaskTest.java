package com.vitorbionic.tasktrackercli.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testToStringFormatting() {
        Task task = new Task(1L, "Study Spring", TaskStatus.TO_DO,
                LocalDateTime.of(2025, 7, 29, 12, 0), null);
        String result = task.toString();
        assertTrue(result.contains("Study Spring"));
        assertTrue(result.contains("TO_DO"));
        assertTrue(result.contains("2025-07-29 12:00"));
    }
}
