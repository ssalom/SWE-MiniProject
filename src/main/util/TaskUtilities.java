package main.util;

import main.service.TaskManagerService;
import main.model.Task;

import java.util.ArrayList;

public class TaskUtilities {

    public static Task getTaskByNumber(int number) {

        //To find a specific value th new Java 8 syntax is used. It's possible to filter by any values with a stream().filter() call
        return TaskManagerService
                .getInstance()
                .getTasks()
                .stream()
                .filter(t -> t.getNumber() == number)
                .findAny()
                .orElse(null);
    }

    public static ArrayList<Task> getAllTasks() {
        return TaskManagerService.getInstance().getTasks();
    }
}
