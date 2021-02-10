package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.service.TaskService;
import com.ssa.taskManager.utilities.Localization;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManagerGUIView {
    private TaskService ts = TaskService.getInstance();
    private ObservableList<Task> taskObservableList;
    private ObservableList<String> statesObservableList;
    private ObservableList<String> prioritiesObservableList;
    private TaskController tc = new TaskController();
    private Stage stage;

    @FXML
    private AnchorPane taskForm;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField taskShortDescription;

    @FXML
    private Label taskNr;

    @FXML
    private TextArea taskDescription;

    @FXML
    private ComboBox taskState;

    @FXML
    private ComboBox taskPriority;

    @FXML
    private TableView tasks;

    @FXML
    private TableColumn columnNr;

    @FXML
    private TableColumn columnShortDescription;

    @FXML
    private TableColumn columnState;

    @FXML
    private TableColumn columnPriority;


    @FXML
    private void initialize() {
        taskObservableList = FXCollections.observableList(ts.getTaskList());
        /*
         * Setzten der Consumer Call Back functions um die observable List synchron zu halten.
         */
        ts.setAddTaskCallback(task -> Platform.runLater(() -> taskObservableList.add(task)));
        ts.setRemoveTaskCallback(task -> Platform.runLater(() -> taskObservableList.remove(task)));


        statesObservableList = FXCollections.observableArrayList(ts.getStates());
        prioritiesObservableList = FXCollections.observableArrayList(ts.getPriorities());
        taskState.setItems(statesObservableList);
        taskPriority.setItems(prioritiesObservableList);


        if (!ts.getTaskList().isEmpty()) {
            toggleTaskFormOn();
        }
        //Set Cell Value Factory for fields in table to map data in task to columns
        columnNr.setCellValueFactory(new PropertyValueFactory<>("number"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("state"));
        columnShortDescription.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));

        tasks.setItems(taskObservableList);
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    private void toggleTaskFormOn () {
        if (taskForm.isDisable()) {
            taskForm.setDisable(false);
        }
    }

    private void loadTaskIntoTaskForm (Task task) {
        tc.loadModel(task);
        taskNr.setText(tc.getNumberDisplayValue());
        taskShortDescription.setText(tc.getShortDescription());
        taskDescription.setText(tc.getDescription());
        taskState.setValue(tc.getStateDisplayValue());
        taskPriority.setValue(tc.getPriorityDisplayValue());
        toggleTaskFormOn();
    }

    private boolean updateTaskFromTaskForm () {
        if (tc.getTask() != null) {
            tc.setShortDescription(taskShortDescription.getText());
            tc.setDescription(taskDescription.getText());
            tc.setPriority(taskPriority.getSelectionModel().getSelectedIndex());
            tc.setState(taskState.getSelectionModel().getSelectedIndex());
            return true;
        }

        return false;
    }


    public void initNewTask(ActionEvent actionEvent) {
        loadTaskIntoTaskForm(ts.createNewTask());
        statusLabel.setText(Localization.getLabels().getString("task-created"));
    }

    public void saveTask(ActionEvent actionEvent) {
        Task task = tc.getTask();
        if (updateTaskFromTaskForm()) {
            statusLabel.setText(Localization.getLabels().getString("task-saved"));
            ts.saveTask(task);
        }
    }

    public void deleteTask(ActionEvent actionEvent) {
        ts.removeTaskFromList(tc.getTask());
        statusLabel.setText(Localization.getLabels().getString("task-deleted"));
    }

    public void loadExistingTask(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            SelectionModel<Task> selectionModel = tasks.getSelectionModel();
            Task task = selectionModel.getSelectedItem();
            loadTaskIntoTaskForm(ts.getTaskByNumber(task.getNumber()));
        }
    }

    public void showSettings(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings-dialog.fxml"));
            Parent root = fxmlLoader.load();
            Dialog settingsDialog = new Dialog();
            settingsDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            settingsDialog.getDialogPane().setContent(root);
            settingsDialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
