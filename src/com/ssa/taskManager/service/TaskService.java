package com.ssa.taskManager.service;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.utilities.ConsoleInputUtilities;
import com.ssa.taskManager.utilities.ConsoleOutputUtilities;

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
        //ToDO Printout of Task didn't work.

        if (!taskList.isEmpty()) {
            showAllTasksOnConsole();
            System.out.println("choose_task_by_number");
            TaskController tc = new TaskController(getTaskByNumber(ConsoleInputUtilities.readInt()));
            tc.printOutTaskDetails();
        } else {
            System.out.println("no_tasks_created.");
        }
    }

    public void showAllTasksOnConsole () {
        if (!taskList.isEmpty()) {
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
            System.out.println(ConsoleOutputUtilities.generateTable(headerList, rows));
        } else {
            System.out.println("no_tasks_created.");
        }
    }

    public void removeTaskOnConsole () {
        if (!taskList.isEmpty()) {
            showAllTasksOnConsole();
            System.out.print("task_select_task: ");
            TaskController tc = new TaskController(getTaskByNumber(ConsoleInputUtilities.readInt()));
            taskList.remove(tc.getTask());
        } else {
            System.out.println("no_tasks_created.");
        }
    }

    public void editTaskOnConsole () {
        if (!taskList.isEmpty()) {
            showAllTasksOnConsole();
            System.out.print("task_select_task: ");
            TaskController tc = new TaskController(getTaskByNumber(ConsoleInputUtilities.readInt()));

            System.out.print("task_shortDescription : ");
            String shortDescitpion = ConsoleInputUtilities.readString();
            if (!shortDescitpion.isEmpty()) {
                tc.setShortDescription(shortDescitpion);
            }

            System.out.print("task_description : ");
            String description = ConsoleInputUtilities.readString();
            if (!description.isEmpty()) {
                tc.setDescription(description);
            }

            System.out.print("task_priority : ");
            tc.setPriority(ConsoleInputUtilities.readInt());

            System.out.print("task_state : ");
            tc.setState(ConsoleInputUtilities.readInt());

            System.out.println("task_updated");
            tc.printOutTaskDetails();
        } else {
            System.out.println("no_tasks_created.");
        }

    }
}
