package com.vitorbionic.tasktrackercli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CliApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testNoArgsPrintsUsage() {
        TaskTrackerCliApplication.main(new String[]{});
        assertTrue(outContent.toString().contains("Usage: task-cli"));
    }
}
