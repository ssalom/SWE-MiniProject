package main.service;

import main.controller.ConsoleController;
import main.model.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManagerService {
    private static final TaskManagerService tc = new TaskManagerService();
    private int taskNumber = 1;
    private ArrayList<Task> tasks = new ArrayList<>();

    private TaskManagerService() {
        //System.out.println(MessageService.getInstance().getMessageByKey("start").getMessage());
    }

    public static TaskManagerService getInstance() {
        return tc;
    }

    public void increaseTaskNumber() {
        taskNumber++;
    }

    public void addTask() throws IOException {
        ConsoleController cc = new ConsoleController();
        Task task = new Task(taskNumber);
        task = cc.getAddTaskDialog(task);

        task.setState(1); //Open
        tasks.add(task);

    }

    public void removeTaskFromTaskList (Task task) {
        TaskManagerService
                .getInstance()
                .getTasks()
                .remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }







}


