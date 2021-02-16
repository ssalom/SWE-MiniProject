package com.ssa.taskManager.mapper;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskMapper {

    public static Task mapResultSetToTask(ResultSet result, Task task, List<Choice> priorities, List<Choice> states) {

        try {
            TaskController tc = new TaskController(task);
            tc.setNumber(result.getInt(2));
            tc.setShortDescription(result.getString(3));
            tc.setDescription(result.getString(4));
            tc.setState(getState(states, result.getInt(5)));
            tc.setPriority(getPriority(priorities, result.getInt(6)));
            return tc.getTask();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private static Choice getPriority(List<Choice> priorities, int id) {
        return priorities.stream().filter(priority -> priority.getId() == id).findFirst().orElse(null);
    }

    private static Choice getState(List<Choice> states, int id) {
        return states.stream().filter(state -> state.getId() == id).findFirst().orElse(null);
    }
}
