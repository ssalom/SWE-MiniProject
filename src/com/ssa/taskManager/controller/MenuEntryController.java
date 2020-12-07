package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.MenuEntry;

public class MenuEntryController {
    private MenuEntry model;

    public MenuEntryController(MenuEntry model) {
        this.model = model;
    }

    public String getMenuEntryName () {
        return model.getName();
    }

    public int getMenuEntryId () {
        return model.getId();
    }

    public void onSelectAction () {
        model.onSelectAction();
    }
}
