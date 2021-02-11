package com.ssa.taskManager.mapper;

import com.ssa.taskManager.controller.ChoiceController;
import com.ssa.taskManager.model.Choice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceMapper {
    public static Choice mapResultSetToChoice (ResultSet result, Choice choice) {
        try {
            ChoiceController cc = new ChoiceController(choice);
            cc.setValue(result.getString(2));
            cc.setOrder(result.getInt(3));
            cc.setFieldName(result.getString(4));
            if (result.getInt(5) == 0) {
                cc.setDefaultValue(false);
            }
            if (result.getInt(5) == 1) {
                cc.setDefaultValue(true);
            }
            return cc.getChoice();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
