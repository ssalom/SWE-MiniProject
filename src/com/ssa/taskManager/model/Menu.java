package com.ssa.taskManager.model;

public class Menu {
    protected String name;
    protected MenuEntry[] menuEntries;

    public Menu(String name, MenuEntry[] menuEntries) {
        this.name = name;
        this.menuEntries = menuEntries;
    }

    public String getName() {
        return name;
    }

    public MenuEntry[] getMenuEntries() {
        return menuEntries;
    }
}
