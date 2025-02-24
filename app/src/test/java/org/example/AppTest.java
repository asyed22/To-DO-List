package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testAddTask() {
        App todoList = new App();
        todoList.add("Buy milk");
        assertEquals(1, todoList.all().size(), "Task was not added.");
    }

    @Test
    public void testCompleteTask() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.complete("Buy milk");
        assertTrue(todoList.complete().get(0).isComplete(), "Task was not marked as complete.");
    }

    @Test
    public void testRejectBlankTask() {
        App todoList = new App();
        todoList.add(""); // Attempt to add a blank task
        assertEquals(0, todoList.all().size(), "Blank task was added.");
    }

    @Test
    public void testRejectDuplicateIncompleteTask() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.add("Buy milk"); // Attempt to add a duplicate incomplete task
        assertEquals(1, todoList.all().size(), "Duplicate incomplete task was added.");
    }

    @Test
    public void testAllowDuplicateCompleteTask() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.complete("Buy milk"); // Mark task as complete
        todoList.add("Buy milk"); // Attempt to add a duplicate complete task
        assertEquals(2, todoList.all().size(), "Duplicate complete task was not allowed.");
    }

    @Test
    public void testClearTasks() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.add("Buy eggs");
        todoList.clear();
        assertEquals(0, todoList.all().size(), "Todo list was not cleared.");
    }

    @Test
    public void testViewCompletedTasks() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.add("Buy eggs");
        todoList.complete("Buy milk");
        List<App.Task> completedTasks = todoList.complete();
        assertEquals(1, completedTasks.size(), "Completed tasks count is incorrect.");
        assertEquals("Buy milk", completedTasks.get(0).getName(), "Completed task name is incorrect.");
    }

    @Test
    public void testViewIncompleteTasks() {
        App todoList = new App();
        todoList.add("Buy milk");
        todoList.add("Buy eggs");
        todoList.complete("Buy milk");
        List<App.Task> incompleteTasks = todoList.incomplete();
        assertEquals(1, incompleteTasks.size(), "Incomplete tasks count is incorrect.");
        assertEquals("Buy eggs", incompleteTasks.get(0).getName(), "Incomplete task name is incorrect.");
    }

    @Test
    public void testTaggedTasks() {
        App todoList = new App();
        todoList.add("Buy milk", Arrays.asList("food"));
        todoList.add("Buy eggs", Arrays.asList("food"));
        todoList.add("Sow beet seeds", Arrays.asList("garden"));
        assertEquals(2, todoList.taggedWith("food").size(), "Tagged tasks count is incorrect.");
        assertEquals(1, todoList.taggedWith("garden").size(), "Tagged tasks count is incorrect.");
    }
}