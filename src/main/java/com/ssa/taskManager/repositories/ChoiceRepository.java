package com.ssa.taskManager.repositories;

import com.ssa.taskManager.config.TaskManagerDBConnection;
import com.ssa.taskManager.controller.ChoiceController;
import com.ssa.taskManager.mapper.ChoiceMapper;
import com.ssa.taskManager.model.Choice;;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceRepository {
    public static List<Choice> getChoices (String fieldName) {
        String sql = "SELECT * FROM `taskManager`.`choices` WHERE `fieldName`=? ORDER BY `order`";
        Connection connection = TaskManagerDBConnection.openConnection();
        List<Choice> choices = new ArrayList<>();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, fieldName);

            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                choices.add(ChoiceMapper.mapResultSetToChoice(result, new Choice()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return choices;
    }

    public static boolean updateFieldValue (String fieldValue, int id)  {
        String sql = "UPDATE `taskManager`.`choices` SET `value`=? WHERE id=?";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, fieldValue);
            prepStatement.setInt(2, id);
            return prepStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean updateOrder (int order, int id) {
        String sql = "UPDATE `taskManager`.`choices` SET `order`=? WHERE id=?";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, order);
            prepStatement.setInt(2, id);
            return prepStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean updateDefaultValue (Choice choice) {
        ChoiceController cc = new ChoiceController(choice);

        //To avoid two default values we need to run here multiple statements
        String sqlUpdatePreviousDefaulValue = "UPDATE `taskManager`.`choices` SET `defaultValue`=0 WHERE `defaultValue`=1 AND `fieldName`=?";
        String sqlUpdateNewDefaultValue = "UPDATE `taskManager`.`choices` SET `defaultValue`=1 WHERE id=?";

        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sqlUpdatePreviousDefaulValue);
            prepStatement.setString(1,cc.getFieldName());
            prepStatement.execute();

            prepStatement = connection.prepareStatement(sqlUpdateNewDefaultValue);
            prepStatement.setInt(1,cc.getId());
            return prepStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void createNewChoice (Choice choice) {
        ChoiceController cc = new ChoiceController(choice);
        String sql = "INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`) VALUES (?, ?, ?, ?)";
        Connection connection = TaskManagerDBConnection.openConnection();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, cc.getValue());
            prepStatement.setInt(2, cc.getOrder());
            prepStatement.setString(3, cc.getFieldName());
            prepStatement.setInt(4, 0);
            if (cc.isDefaultValue()) {
                prepStatement.setInt(4, 1);
            }
            prepStatement.execute();
            ResultSet result = prepStatement.getGeneratedKeys();
            if (result.next()) {
                cc.setId(result.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
