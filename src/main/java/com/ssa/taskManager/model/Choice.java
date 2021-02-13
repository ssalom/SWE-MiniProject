package com.ssa.taskManager.model;

import javafx.beans.property.*;

public class Choice {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty value = new SimpleStringProperty();
    private StringProperty fieldName = new SimpleStringProperty();
    private IntegerProperty order = new SimpleIntegerProperty();
    public BooleanProperty defaultValue = new SimpleBooleanProperty(false);

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getFieldName() {
        return fieldName.get();
    }

    public StringProperty fieldNameProperty() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName.set(fieldName);
    }

    public int getOrder() {
        return order.get();
    }

    public IntegerProperty orderProperty() {
        return order;
    }

    public void setOrder(int order) {
        this.order.set(order);
    }

    public boolean isDefaultValue() {
        return  defaultValue.get();
    }

    public BooleanProperty defaultValueProperty() {
        return defaultValue;
    }

    public void setDefaultValue (boolean defaultValue) {
        this.defaultValue.set(defaultValue);
    }

    public String toString() {
        return value.get();
    }
}
