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
    private TaskService taskService;

    public TaskManagerConsoleView(TaskService taskService) {
        this.taskService = taskService;
    }

    public void addTaskOnConsole () {
        TaskController tc = new TaskController( taskService.createNewTask());
        System.out.print(Localization.getLabels().getString("task-shortDescription") + ": ");
        tc.setShortDescription(ConsoleInputUtilities.readString());

        System.out.print(Localization.getLabels().getString("task-description") + ": ");
        tc.setDescription(ConsoleInputUtilities.readString());

        System.out.print(Localization.getLabels().getString("task-priority") + ": ");
        tc.setPriority(ConsoleInputUtilities.readInt());

        taskService.addTaskToList(tc.getTask());
    }

    public void showTaskOnConsole () {
        //ToDO Printout of Task didn't work.

        if (!taskService.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.println(Localization.getLabels().getString("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(taskService.getTaskByNumber(ConsoleInputUtilities.readInt()));
            tc.printOutTaskDetails();
        } else {
            System.out.println(Localization.getLabels().getString("no-tasks-created"));
        }
    }

    public void showAllTasksOnConsole () {
        if (!taskService.getTaskList().isEmpty()) {
            List<String> headerList = new ArrayList<>();
            List<List<String>> rows = new ArrayList<>();
            headerList.add(Localization.getLabels().getString("task-number"));
            headerList.add(Localization.getLabels().getString("task-shortDescription"));
            headerList.add(Localization.getLabels().getString("task-state"));
            headerList.add(Localization.getLabels().getString("task-priority"));

            taskService.getTaskList().forEach((Task task) -> {
                TaskController tc = new TaskController(task);
                List<String> col = tc.getTaskColumnForlist();
                rows.add(col);
            });
            System.out.println(ConsoleOutputUtilities.generateTable(headerList, rows));
        } else {
            System.out.println(Localization.getLabels().getString("no-tasks-created"));
        }
    }

    public void removeTaskOnConsole () {
        if (!taskService.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.print(Localization.getLabels().getString("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(taskService.getTaskByNumber(ConsoleInputUtilities.readInt()));
            taskService.removeTaskFromList(tc.getTask());
        } else {
            System.out.println(Localization.getLabels().getString("no-tasks-created"));
        }
    }

    public void editTaskOnConsole () {
        if (!taskService.getTaskList().isEmpty()) {
            showAllTasksOnConsole();
            System.out.print(Localization.getLabels().getString("choose-task-by-number") + ": ");
            TaskController tc = new TaskController(taskService.getTaskByNumber(ConsoleInputUtilities.readInt()));

            System.out.print(Localization.getLabels().getString("task-shortDescription") + ": ");
            String shortDescitpion = ConsoleInputUtilities.readString();
            if (!shortDescitpion.isEmpty()) {
                tc.setShortDescription(shortDescitpion);
            }

            System.out.print(Localization.getLabels().getString("task-description") + ": ");
            String description = ConsoleInputUtilities.readString();
            if (!description.isEmpty()) {
                tc.setDescription(description);
            }

            System.out.print(Localization.getLabels().getString("task-priority") + ": ");
            tc.setPriority(ConsoleInputUtilities.readInt());

            System.out.print(Localization.getLabels().getString("task-state") + ": ");
            tc.setState(ConsoleInputUtilities.readInt());

            System.out.println(Localization.getLabels().getString("task-updated"));
            tc.printOutTaskDetails();
        } else {
            System.out.println(Localization.getLabels().getString("no-tasks-created"));
        }

    }

}
