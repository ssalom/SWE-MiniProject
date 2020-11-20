package com.ssa.taskManager.model;

public class MenuEntry {
    private int id;
    private String name;
    private int order;

    public MenuEntry(int id, String message, int order) {
        this.id = id;
        this.name = message;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
