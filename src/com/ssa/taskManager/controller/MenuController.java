package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Menu;
import com.ssa.taskManager.model.MenuEntry;
import com.ssa.taskManager.view.MenuView;
import main.service.MessageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MenuController {
    private Menu model;
    private MenuView view = new MenuView();

    public MenuController(Menu model) {
        this.model = model;
    }

    public String getMenuName () {
        return model.getName();
    }

    public MenuEntry[] getMenuEntries () {
        return model.getMenuEntries();
    }

    public void printOutMenu() {
        view.printOutMenu(getMenuName(), getMenuEntries());
    }



}
