package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Choice;

public class ChoiceController {
    private Choice model;

    public ChoiceController() {
    }

    public ChoiceController(Choice model) {
        this.model = model;
    }

    public void createChoice(String fieldName) {
        model = new Choice();
        model.setValue("NewChoice");
        model.setFieldName(fieldName);
        model.setDefaultValue(false);
    }

    public void loadModel (Choice model) {
        this.model = model;
    }

    public Choice getChoice () {
        return model;
    }

    public void setId(int id) {
        model.setId(id);
    }

    public int getId() {
        return model.getId();
    }

    public String getValue() {
        return model.getValue();
    }

    public void setValue(String value) {
        model.setValue(value);
    }

    public String getFieldName() {
        return model.getFieldName();
    }

    public void setFieldName(String fieldName) {
        model.setFieldName(fieldName);
    }

    public int getOrder() {
        return model.getOrder();
    }

    public void setOrder(int order) {
        model.setOrder(order);
    }

    public boolean isDefaultValue() {
        return model.isDefaultValue();
    }

    public void setDefaultValue (boolean defaultValue) {
        model.setDefaultValue(defaultValue);
    }


}
