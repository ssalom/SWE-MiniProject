package com.ssa.taskManager.service;

import com.ssa.taskManager.controller.MenuController;
import com.ssa.taskManager.controller.MenuEntryController;
import com.ssa.taskManager.model.Menu;
import com.ssa.taskManager.model.MenuEntry;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleService {
    private TaskService ts = new TaskService();
    private MenuController generateMainMenu () {
        MenuEntry addTaskMainMenuEntry = new MenuEntry(1, "add_task", 1) {
            @Override
            public void onSelectAction() {
                ts.addTaskOnConsole();
            }
        };
        MenuEntry showTaskMainMenuEntry = new MenuEntry(2, "show_task", 2) {
            @Override
            public void onSelectAction() {
                ts.showTaskOnConsole();
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
                ts.showAllTasksOnConsole();
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

    private void displayMainMenu() {
        MenuController menuController = generateMainMenu();
        handleMenuDisplay(menuController);
    }

    public void runTaskManagerOnConsole ()  {
        displayMainMenu();
    }

    public void handleMenuDisplay (MenuController mc) {
        while (true) {
            mc.printOutMenu();
            MenuEntryController selectedMenuEntry = selectMenuEntry(mc.getMenuEntries());
            selectedMenuEntry.onSelectAction();
        }
    }

    public MenuEntryController selectMenuEntry (MenuEntry[] menuEntries) {
        while (true) {
            int selection = readMenuSelection();
            if (isSelectedMenuEntryValid(selection, menuEntries)) {
                return new MenuEntryController(Arrays.stream(menuEntries)
                        .filter(m -> m.getId() == selection)
                        .findFirst()
                        .get());
            } else {
                System.out.println("wrong_menu_entry");
            }
        }
    }

    private boolean isSelectedMenuEntryValid (int selection, MenuEntry[] menuEntries) {
        return Arrays.stream(menuEntries).anyMatch(m -> m.getId() == selection);
    }

    public int readMenuSelection () {
        Scanner scanner = new Scanner(System.in);
        boolean firstTry = true;

        do {
            if (!firstTry) {
                System.out.println("wrong_user_input_non_int");
                System.out.println();
                scanner.nextLine();
            } else {
                firstTry = false;
            }
            System.out.print("user_input : ");
        } while (!scanner.hasNextInt());
        return scanner.nextInt();
    }



}
