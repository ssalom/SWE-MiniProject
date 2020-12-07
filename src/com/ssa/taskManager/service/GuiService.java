package com.ssa.taskManager.service;

import com.ssa.taskManager.utilities.Localization;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiService extends Application {


    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createRootContainer(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(Localization.getLabels().getString("app-title"));
        primaryStage.show();
    }

    public BorderPane createRootContainer () {
        BorderPane root = new BorderPane();
        root.setTop(createNavigation());
        return root;
    }

    public MenuBar createNavigation () {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu(Localization.getLabels().getString("file"));

        MenuItem newTaskMenuItem = new MenuItem(Localization.getLabels().getString("add-task"));
        MenuItem settingsMenuItem = new MenuItem(Localization.getLabels().getString("settings"));
        MenuItem exitMenuItem = new MenuItem(Localization.getLabels().getString("exit"));
        menu.getItems().addAll(newTaskMenuItem, settingsMenuItem, exitMenuItem);

        menuBar.getMenus().add(menu);

        return menuBar;
    }


}
