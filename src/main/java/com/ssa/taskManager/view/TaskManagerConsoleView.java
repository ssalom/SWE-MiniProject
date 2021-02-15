package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.service.TaskService;
import com.ssa.taskManager.utilities.ConsoleInputUtilities;
import com.ssa.taskManager.utilities.ConsoleOutputUtilities;
import com.ssa.taskManager.utilities.Localization;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerConsoleView {
    private TaskService ts = TaskService.getInstance();
    private List<Task> taskList;

    public TaskManagerConsoleView() {
        taskList = ts.getTaskList();
    }

    public void addTaskOnConsole() {
        TaskController tc = new TaskController(ts.createNewTask());
        System.out.print(Localization.getTranslation("task-shortDescription") + ": ");
        tc.setShortDescription(ConsoleInputUtilities.readString());

        System.out.print(Localization.getTranslation("task-description") + ": ");
        tc.setDescription(ConsoleInputUtilities.readString());

        System.out.print(Localization.getTranslation("task-priority") + ": ");
        tc.setPriority(ts.getPriorityByValue(ConsoleInputUtilities.readString()));

        ts.saveTask(tc.getTask());
        taskList.add(tc.getTask());
    }

    public void showTaskOnConsole () {
        //ToDO Printout of Task didn't work.

        if (!ts.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.println(Localization.getTranslation("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(ts.getTaskByNumber(ConsoleInputUtilities.readInt()));
            tc.printOutTaskDetails();
        } else {
            System.out.println(Localization.getTranslation("no-tasks-created"));
        }
    }

    public void showAllTasksOnConsole () {
        if (!ts.getTaskList().isEmpty()) {
            List<String> headerList = new ArrayList<>();
            List<List<String>> rows = new ArrayList<>();
            headerList.add(Localization.getTranslation("task-number"));
            headerList.add(Localization.getTranslation("task-shortDescription"));
            headerList.add(Localization.getTranslation("task-state"));
            headerList.add(Localization.getTranslation("task-priority"));

            ts.getTaskList().forEach((Task task) -> {
                TaskController tc = new TaskController(task);
                List<String> col = tc.getTaskColumnForlist();
                rows.add(col);
            });
            System.out.println(ConsoleOutputUtilities.generateTable(headerList, rows));
        } else {
            System.out.println(Localization.getTranslation("no-tasks-created"));
        }
    }

    public void removeTaskOnConsole () {
        if (!ts.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.print(Localization.getTranslation("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(ts.getTaskByNumber(ConsoleInputUtilities.readInt()));
            ts.removeTaskFromList(tc.getTask());
            taskList.remove(tc.getTask());
        } else {
            System.out.println(Localization.getTranslation("no-tasks-created"));
        }
    }

    public void editTaskOnConsole () {
        if (!ts.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.print(Localization.getTranslation("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(ts.getTaskByNumber(ConsoleInputUtilities.readInt()));

            System.out.print(Localization.getTranslation("task-shortDescription") + ": ");
            String shortDescitpion = ConsoleInputUtilities.readString();
            if (!shortDescitpion.isEmpty()) {
                tc.setShortDescription(shortDescitpion);
            }

            System.out.print(Localization.getTranslation("task-description") + ": ");
            String description = ConsoleInputUtilities.readString();
            if (!description.isEmpty()) {
                tc.setDescription(description);
            }

            System.out.print(Localization.getTranslation("task-priority") + ": ");
            tc.setPriority(ts.getPriorityByValue(ConsoleInputUtilities.readString()));

            System.out.print(Localization.getTranslation("task-state") + ": ");
            tc.setState(ts.getStateByValue(ConsoleInputUtilities.readString()));

            ts.saveTask(tc.getTask());

            System.out.println(Localization.getTranslation("task-updated"));
            tc.printOutTaskDetails();
        } else {
            System.out.println(Localization.getTranslation("no-tasks-created"));
        }

    }
}
