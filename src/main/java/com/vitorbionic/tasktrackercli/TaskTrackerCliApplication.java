package com.vitorbionic.tasktrackercli;

import java.util.Map;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vitorbionic.tasktrackercli.model.TaskStatus;
import com.vitorbionic.tasktrackercli.service.TaskService;

@SpringBootApplication
public class TaskTrackerCliApplication {

	public static void main(String[] args) {
	    SpringApplication application = 
	            new SpringApplication(TaskTrackerCliApplication.class);
	    application.setBannerMode(Banner.Mode.OFF);
	    application.setDefaultProperties(Map.of("logging.level.root", "OFF"));
	    ConfigurableApplicationContext context = application.run(args);
	    
	    if (args.length == 0) {
	        System.out.println("Usage: task-cli <command> [options]");
	        return;
	    }
	    
	    TaskService taskService = context.getBean(TaskService.class);

        switch (args[0]) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli add \"task description\"");
                    break;
                }
                taskService.addTask(args[1]);
                break;
            case "update":
                if (args.length < 3) {
                    System.out.println("Usage: task-cli update <taskId> \"new task description\"");
                    break;
                }
                try {
                    taskService.updateTask(Long.valueOf(args[1]), args[2]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli delete <taskId>");
                    break;
                }
                try {
                    taskService.deleteTask(Long.valueOf(args[1]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "mark-in-progress":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli mark-in-progress <taskId>");
                    break;
                }
                try {
                    taskService.markAsInProgress(Long.valueOf(args[1]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "mark-done":
                if (args.length < 2) {
                    System.out.println("Usage: task-cli mark-done <taskId>");
                    break;
                }
                try {
                    taskService.markAsDone(Long.valueOf(args[1]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "list":
                if (args.length == 1)
                    taskService.listAll();
                else {
                    if (!args[1].equals("todo") && !args[1].equals("in-progress") && !args[1].equals("done")) {
                        System.out.println("Usage: task-cli list [todo|in-progress|done]");
                        break;
                    }
                    
                    if (args[1].equals("todo")) {
                        taskService.listAllByStatus(TaskStatus.TO_DO);
                    } else if (args[1].equals("in-progress")) {
                        taskService.listAllByStatus(TaskStatus.IN_PROGRESS);
                    } else {
                        taskService.listAllByStatus(TaskStatus.DONE);
                    }
                }
                break;
            case "--help":
            case "help":
                printHelp();
                break;
            default:
                System.out.println("Unknown command: " + args[0]);
                System.out.println("Use \"task-cli --help\" to see available commands.");
        }
	}
	
	private static void printHelp() {
	    System.out.println("Task Tracker CLI");
	    System.out.println("Usage: task-cli <command> [options]");
	    System.out.println();
	    System.out.println("Commands:");
	    System.out.println("  add \"description\"          Add a new task");
	    System.out.println("  update <id> \"description\"  Update task description");
	    System.out.println("  delete <id>                 Delete a task");
	    System.out.println("  mark-in-progress <id>       Mark a task as in progress");
	    System.out.println("  mark-done <id>              Mark a task as done");
	    System.out.println("  list [todo|in-progress|done] List tasks (all or by status)");
	    System.out.println("  help, --help                Show this help message");
	}
}
