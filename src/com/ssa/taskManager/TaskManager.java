package com.ssa.taskManager;

import com.ssa.taskManager.service.ConsoleService;

import java.io.IOException;

public class TaskManager {

    public static void main (String[] args) throws IOException {
        ConsoleService consoleController = new ConsoleService();
        consoleController.runTaskManagerOnConsole();
    }


}
