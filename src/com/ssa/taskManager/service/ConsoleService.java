package com.ssa.taskManager.service;

import com.ssa.taskManager.controller.MenuController;
import com.ssa.taskManager.controller.MenuEntryController;
import com.ssa.taskManager.model.Menu;
import com.ssa.taskManager.model.MenuEntry;
import com.ssa.taskManager.utilities.Localization;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleService {
    private TaskService ts = new TaskService();
    private MenuController generateMainMenu () {
        MenuEntry addTaskMainMenuEntry = new MenuEntry(1, Localization.getLabels().getString("add-task"), 1) {
            @Override
            public void onSelectAction() {
                ts.addTaskOnConsole();
            }
        };
        MenuEntry showTaskMainMenuEntry = new MenuEntry(2, Localization.getLabels().getString("show-task"), 2) {
            @Override
            public void onSelectAction() {
                ts.showTaskOnConsole();
            }
        };
        MenuEntry editTaskMainMenuEntry = new MenuEntry(3, Localization.getLabels().getString("edit-task"), 3) {
            @Override
            public void onSelectAction() {
                ts.editTaskOnConsole();
            }
        };
        MenuEntry deleteTaskMainMenuEntry = new MenuEntry(4, Localization.getLabels().getString("delete-task"), 4) {
            @Override
            public void onSelectAction() {
                ts.removeTaskOnConsole();
            }
        };
        MenuEntry showAllTaskMainMenuEntry = new MenuEntry(5, Localization.getLabels().getString("show-all-tasks"), 5) {
            @Override
            public void onSelectAction() {
                ts.showAllTasksOnConsole();
            }
        };
        MenuEntry exitMainMenuEntry = new MenuEntry(6, Localization.getLabels().getString("exit"), 6) {
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
        return new MenuController(new Menu(Localization.getLabels().getString("main-menu"), menuEntries));
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
                System.out.println(Localization.getLabels().getString("wrong-menu-entry"));
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
                System.out.println(Localization.getLabels().getString("wrong-user-input-non-int"));
                System.out.println();
                scanner.nextLine();
            } else {
                firstTry = false;
            }
            System.out.print(Localization.getLabels().getString("user-input") + ": ");
        } while (!scanner.hasNextInt());
        return scanner.nextInt();
    }



}
