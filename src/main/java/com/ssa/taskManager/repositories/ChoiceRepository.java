package com.ssa.taskManager.repositories;

import com.ssa.taskManager.config.TaskManagerDBConnection;
import com.ssa.taskManager.mapper.ChoiceMapper;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceRepository {
    public static List<Choice> getPriorities () {
        String sql = "SELECT * FROM choices WHERE fieldName='priority'";
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Choice> choices = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                choices.add(ChoiceMapper.mapResultSetToChoice(result, new Choice()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return choices;
    }

    public static List<Choice> getStates () {
        String sql = "SELECT * FROM choices WHERE fieldName='state'";
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Choice> choices = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                choices.add(ChoiceMapper.mapResultSetToChoice(result, new Choice()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return choices;
    }


}
