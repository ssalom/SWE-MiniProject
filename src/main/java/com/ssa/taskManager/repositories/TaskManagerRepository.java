package com.ssa.taskManager.repositories;

import com.ssa.taskManager.config.TaskManagerDBConnection;
import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.mapper.TaskMapper;
import com.ssa.taskManager.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerRepository {

    public static boolean createTask (Task task) {
        TaskController tc = new TaskController(task);
        String sql = "INSERT INTO task (nr, shortDescription, description, state, priority) VALUES (?, ?, ?, ?, ?)";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tc.getNumber());
            preparedStatement.setString(2, tc.getShortDescription());
            preparedStatement.setString(3, tc.getDescription());
            preparedStatement.setInt(4, tc.getState());
            preparedStatement.setInt(5, tc.getPriority());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean updateTask (Task task) {
        TaskController tc = new TaskController(task);
        String sql = "UPDATE task SET shortDescription=?, description=?, state=?, priority=? WHERE nr=?";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tc.getShortDescription());
            preparedStatement.setString(2, tc.getDescription());
            preparedStatement.setInt(3, tc.getState());
            preparedStatement.setInt(4, tc.getPriority());
            preparedStatement.setInt(5, tc.getNumber());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteTask (Task task) {
        TaskController tc = new TaskController(task);
        String sql = "DELETE FROm taskManager.task WHERE nr=?";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tc.getNumber());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static List<Task> getAllTasks () {
        String sql = "SELECT * FROM task";
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                tasks.add(TaskMapper.mapResultSetToTask(result, new Task()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tasks;
    }
}
