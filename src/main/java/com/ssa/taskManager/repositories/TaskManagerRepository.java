package com.ssa.taskManager.repositories;

import com.ssa.taskManager.config.TaskManagerDBConnection;
import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.mapper.TaskMapper;
import com.ssa.taskManager.model.Choice;
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
            preparedStatement.setInt(4, tc.getState().getId());
            preparedStatement.setInt(5, tc.getPriority().getId());
            if (preparedStatement.execute()) {
                TaskManagerDBConnection.closeConnection(connection);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TaskManagerDBConnection.closeConnection(connection);
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
            preparedStatement.setInt(3, tc.getState().getId());
            preparedStatement.setInt(4, tc.getPriority().getId());
            preparedStatement.setInt(5, tc.getNumber());
            if (preparedStatement.execute()) {
                TaskManagerDBConnection.closeConnection(connection);
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TaskManagerDBConnection.closeConnection(connection);
        return false;
    }

    public static boolean deleteTask (Task task) {
        TaskController tc = new TaskController(task);
        String sql = "DELETE FROm taskManager.task WHERE nr=?";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tc.getNumber());
            if (preparedStatement.execute()) {
                TaskManagerDBConnection.closeConnection(connection);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        TaskManagerDBConnection.closeConnection(connection);
        return false;
    }

    public static List<Task> getAllTasks(List<Choice> priorities, List<Choice> states) {
        String sql = "SELECT * FROM task";
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                tasks.add(TaskMapper.mapResultSetToTask(result, new Task(), priorities, states));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TaskManagerDBConnection.closeConnection(connection);
        return tasks;
    }
}
