package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    public List<Task> tasks;
    public App() {
        tasks = new ArrayList<>();
    }

    public void add(String name) {
      if (name == null || name.trim().isEmpty()) {
          System.out.println("Task name cannot be blank. Please try again.");
          return;
      }
      for (Task task : tasks) {
          if (task.getName().equalsIgnoreCase(name) && !task.isComplete()) {
              System.out.println("Duplicate incomplete task not allowed: " + name);
              return;
          }
      }
  
      tasks.add(new Task(name));
      System.out.println("Task added: " + name);
  }

    public void add(String name, List<String> tags) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Task name cannot be blank. Please try again.");
            return;
        }
        tasks.add(new Task(name, tags));
        System.out.println("Task added: " + name);
    }

    public void complete(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.setComplete(true);
                System.out.println("Task marked as complete: " + name);
                return;
            }
        }
        System.out.println("Task not found: " + name);
    }

    public List<Task> all() {
        return tasks;
    }

    public List<Task> complete() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isComplete()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public List<Task> incomplete() {
        List<Task> incompleteTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isComplete()) {
                incompleteTasks.add(task);
            }
        }
        return incompleteTasks;
    }

    public void clear() {
        tasks.clear();
        System.out.println("All tasks cleared.");
    }

    public List<Task> taggedWith(String tag) {
        List<Task> taggedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTags().contains(tag)) {
                taggedTasks.add(task);
            }
        }
        return taggedTasks;
    }

    public static class Task {
        private String name;
        private boolean complete;
        private Set<String> tags;

        public Task(String name) {
            this.name = name;
            this.complete = false;
            this.tags = new HashSet<>();
        }

        public Task(String name, List<String> tags) {
            this.name = name;
            this.complete = false;
            this.tags = new HashSet<>(tags);
        }

        public String getName() {
            return name;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public Set<String> getTags() {
            return tags;
        }

        @Override
        public String toString() {
            return name + " [Completed: " + complete + "]";
        }
    }

    public static void main(String[] args) {
        App todoList = new App();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Todo List Menu ---");
            System.out.println("1. Add a task");
            System.out.println("2. Mark a task as complete");
            System.out.println("3. View all tasks");
            System.out.println("4. View completed tasks");
            System.out.println("5. View incomplete tasks");
            System.out.println("6. Clear the todo list");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the task name: ");
                    String taskName = scanner.nextLine();
                    todoList.add(taskName);
                    break;

                case 2:
                    System.out.print("Enter the task name to mark as complete: ");
                    String taskToComplete = scanner.nextLine();
                    todoList.complete(taskToComplete);
                    break;

                case 3:
                    System.out.println("\nAll tasks:");
                    for (Task task : todoList.all()) {
                        System.out.println("- " + task);
                    }
                    break;

                case 4:
                    System.out.println("\nCompleted tasks:");
                    for (Task task : todoList.complete()) {
                        System.out.println("- " + task);
                    }
                    break;

                case 5:
                    System.out.println("\nIncomplete tasks:");
                    for (Task task : todoList.incomplete()) {
                        System.out.println("- " + task);
                    }
                    break;

                case 6:
                    todoList.clear();
                    break;

                case 7:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
