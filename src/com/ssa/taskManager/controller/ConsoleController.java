package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Menu;
import com.ssa.taskManager.model.MenuEntry;

import java.io.IOException;

public class ConsoleController {

    private MenuController generateMainMenu () {
        MenuEntry addTaskMainMenuEntry = new MenuEntry(1, "add_task", 1) {
            @Override
            public void onSelectAction() {
                //ToDO Implement addTask BL
            }
        };
        MenuEntry showTaskMainMenuEntry = new MenuEntry(2, "show_task", 2) {
            @Override
            public void onSelectAction() {
                //ToDO Implement showTask BL
            }
        };
        MenuEntry editTaskMainMenuEntry = new MenuEntry(3, "edit_task", 3) {
            @Override
            public void onSelectAction() {
                //ToDO Implement editTask BL
            }
        };
        MenuEntry deleteTaskMainMenuEntry = new MenuEntry(4, "delete_task", 4) {
            @Override
            public void onSelectAction() {
                //ToDO Implement deleteTask BL
            }
        };
        MenuEntry showAllTaskMainMenuEntry = new MenuEntry(5, "show_all_tasks", 5) {
            @Override
            public void onSelectAction() {
                //ToDO Implement showAllTask BL
            }
        };
        MenuEntry exitMainMenuEntry = new MenuEntry(6, "exit", 6) {
            @Override
            public void onSelectAction() {
                System.exit(0);
            }
        };

        MenuEntry menuEntries[] = {
                addTaskMainMenuEntry,
                showTaskMainMenuEntry,
                editTaskMainMenuEntry,
                deleteTaskMainMenuEntry,
                showAllTaskMainMenuEntry,
                exitMainMenuEntry
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
