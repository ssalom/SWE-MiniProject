package com.ssa.taskManager.mapper;

import com.ssa.taskManager.controller.ChoiceController;
import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceMapper {
    public static Task mapResultSetToChoice (ResultSet result, Choice choice) {

        try {
            ChoiceController cc = new ChoiceController(choice);
            cc.setValue();
            cc.setOrder(1);
            cc.setFieldName();
            cc.setDefaultValue(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
