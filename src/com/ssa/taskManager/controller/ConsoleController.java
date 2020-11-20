package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Menu;
import com.ssa.taskManager.model.MenuEntry;

import java.io.IOException;

public class ConsoleController {

    private MenuController generateMainMenu () {

        MenuEntry menuEntries[] = {
                new MenuEntry(1, "add_task", 1),
                new MenuEntry(2, "show_task", 2),
                new MenuEntry(3, "edit_task", 3),
                new MenuEntry(4, "delete_task", 4),
                new MenuEntry(5, "show_all_tasks", 5),
                new MenuEntry(6, "exit", 6),
        };
        return new MenuController(new Menu("main_menu", menuEntries));
    }

    private void displayMainMenu() throws IOException {
        MenuController menuController = generateMainMenu();
        menuController.handleMenuDisplay();
    }

    public void runTaskManagerOnConsole () throws IOException {
        displayMainMenu();

    }

}
