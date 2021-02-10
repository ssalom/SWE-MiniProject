package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Choice;

public class ChoiceController {
    private Choice model;

    public ChoiceController(Choice model) {
        this.model = model;
    }

    public void loadModel (Choice model) {
        this.model = model;
    }

    public void setValue () {

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
