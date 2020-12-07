package com.ssa.taskManager.view;

import com.ssa.taskManager.utilities.ConsoleOutputUtilities;

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
        headerList.add("task_column_number");
        headerList.add("task_column_short_description");
        headerList.add("task_column_description");
        headerList.add("task_column_state");
        headerList.add("task_column_priority");

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
