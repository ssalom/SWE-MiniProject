package com.ssa.taskManager;

import com.ssa.taskManager.controller.ConsoleController;

import java.io.IOException;

public class TaskManager {

    public static void main (String[] args) throws IOException {
        ConsoleController consoleController = new ConsoleController();
        consoleController.runTaskManagerOnConsole();
    }


}
