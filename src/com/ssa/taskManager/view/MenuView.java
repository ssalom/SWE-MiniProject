package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.MenuEntryController;
import com.ssa.taskManager.model.MenuEntry;
import com.ssa.taskManager.utilities.ConsoleOutputUtilities;
import com.ssa.taskManager.utilities.Localization;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
    public void printOutMenu (String menuName, MenuEntry[] menuEntries) {
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();


        headerList.add(Localization.getLabels().getString("menu-nr"));
        headerList.add(Localization.getLabels().getString("menu-name"));

        for (MenuEntry menuEntry : menuEntries) {
            MenuEntryController menuEntryController = new MenuEntryController(menuEntry);
            List<String> col = new ArrayList<>();
            col.add(Integer.toString(menuEntryController.getMenuEntryId()));
            col.add(menuEntryController.getMenuEntryName());
            rows.add(col);
        }
        System.out.println(ConsoleOutputUtilities.generateTable(headerList, rows));

    }
}
