package com.ssa.taskManager;

import com.ssa.taskManager.service.ConsoleService;
import com.ssa.taskManager.service.GuiService;
import com.ssa.taskManager.utilities.Localization;
import javafx.application.Application;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class TaskManager {

    public static void main (String[] args) throws IOException {
        //ConsoleService consoleService = new ConsoleService();
        //consoleService.runTaskManagerOnConsole();

        Application.launch(GuiService.class, args);
    }


}
