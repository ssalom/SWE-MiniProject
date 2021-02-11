package com.ssa.taskManager.mapper;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper {

    public static Task mapResultSetToTask (ResultSet result, Task task) {

        try {
            TaskController tc = new TaskController(task);
            tc.setNumber(result.getInt(2));
            tc.setShortDescription(result.getString(3));
            tc.setDescription(result.getString(4));
            tc.setState(result.getInt(5));
            tc.setPriority(result.getInt(6));
            return tc.getTask();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
