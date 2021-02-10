package com.ssa.taskManager.model;

public class Choice {
    private String value;
    private String fieldName;
    private int order;
    private boolean defaultValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue (boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
}
