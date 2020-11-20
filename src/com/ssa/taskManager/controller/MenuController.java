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

    public void handleMenuDisplay () throws IOException {

        printOutMenu();
        MenuEntryController selectedMenuEntry = selectMenuEntry();
        if (selectedMenuEntry != null) {
            selectedMenuEntry.getMenuEntryName();
        }
        //ToDo Implement Alert Message Model
        handleMenuDisplay();
    }

    public void printOutMenu() {
        view.printOutMenu(getMenuName(), getMenuEntries());
    }

    public MenuEntryController selectMenuEntry () throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selection = validateSelection(reader.readLine());
        MenuEntry[] menuEntries = model.getMenuEntries();
        if (isSelectedMenuEntryValid(selection, menuEntries)) {
            return new MenuEntryController(Arrays.stream(menuEntries)
                    .filter(m -> m.getId() == selection)
                    .findFirst()
                    .get());
        }
        return null;
    }

    private int validateSelection (String selection) {
        return Integer.parseInt(selection);
    }

    private boolean isSelectedMenuEntryValid (int selection, MenuEntry[] menuEntries) {
        return Arrays.stream(menuEntries).anyMatch(m -> m.getId() == selection);
    }
}
