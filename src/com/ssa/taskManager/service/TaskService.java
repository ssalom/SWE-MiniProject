package com.ssa.taskManager.service;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.utilities.ConsoleInputUtilities;
import main.util.OutputUtilities;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static List<Task> taskList = new ArrayList<>();

    public void addTaskOnConsole () {
        TaskController tc = new TaskController(new Task());
        System.out.print("task_shortDescription : ");
        tc.setShortDescription(ConsoleInputUtilities.readString());

        System.out.print("task_description : ");
        tc.setDescription(ConsoleInputUtilities.readString());

        System.out.print("task_priority : ");
        tc.setPriority(ConsoleInputUtilities.readInt());
        tc.setState(1);

        taskList.add(tc.getTask());
    }

    private Task getTaskByNumber (int number) {
        return taskList.stream()
                .filter(t -> t.getNumber() == number)
                .findAny()
                .orElse(null);

    }

    public void showTaskOnConsole () {
        showAllTasksOnConsole();
        System.out.println("choose_task_by_number");
        TaskController tc = new TaskController(getTaskByNumber(ConsoleInputUtilities.readInt()));
        tc.printOutTaskDetails();
    }

    public void showAllTasksOnConsole () {
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        headerList.add("task_column_number");
        headerList.add("task_column_short_description");
        headerList.add("task_column_state");
        headerList.add("task_column_priority");

        taskList.forEach((Task task) -> {
            TaskController tc = new TaskController(task);
            List<String> col = tc.getTaskColumnForlist();
            rows.add(col);
        });
        System.out.println(OutputUtilities.generateTable(headerList, rows));
    }

    public void removeTaskOnConsole () {
        TaskController tc = new TaskController(new Task());
        showAllTasksOnConsole();
        System.out.print("task_select_task: ");

    }
}
