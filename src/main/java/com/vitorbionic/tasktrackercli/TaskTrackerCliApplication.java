package com.vitorbionic.tasktrackercli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TaskTrackerCliApplication {

	public static void main(String[] args) {
	    SpringApplication application = new SpringApplication(TaskTrackerCliApplication.class);
	    ConfigurableApplicationContext context = application.run(args);
	    
	    if (args.length == 0) {
	        System.out.println("Usage: task-cli <command> [options]");
	        return;
	    }
	}

}
