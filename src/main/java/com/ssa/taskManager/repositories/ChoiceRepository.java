package com.ssa.taskManager.repositories;

import com.ssa.taskManager.config.TaskManagerDBConnection;
import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.mapper.TaskMapper;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceRepository {
    public static void getPriorities () {
        String sql = "SELECT * FROM choice WHERE 'fieldName'='priority';
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Choice> choices = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                choices.add();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return choices;
    }


}
