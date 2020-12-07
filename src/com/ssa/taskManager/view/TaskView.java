package com.ssa.taskManager.view;

import com.ssa.taskManager.utilities.ConsoleOutputUtilities;
import com.ssa.taskManager.utilities.Localization;

import java.util.ArrayList;
import java.util.List;

public class TaskView {
    public void printOutTaskDetails (
            String number,
            String shortDescription,
            String description,
            String state,
            String priority
    ) {
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        headerList.add(Localization.getLabels().getString("task-number"));
        headerList.add(Localization.getLabels().getString("task-shortDescription"));
        headerList.add(Localization.getLabels().getString("task-description"));
        headerList.add(Localization.getLabels().getString("task-state"));
        headerList.add(Localization.getLabels().getString("task-priority"));

        List<String> col = new ArrayList<>();

        col.add(number);
        col.add(shortDescription);
        col.add(description);
        col.add(state);
        col.add(priority);
        rows.add(col);
        System.out.println(ConsoleOutputUtilities.generateTable(headerList, rows));
    }
}
