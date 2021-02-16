package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.service.TaskService;
import com.ssa.taskManager.utilities.Localization;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagerGUIView {
    private TaskService ts = TaskService.getInstance();
    private ObservableList<Task> taskObservableList;
    private ObservableList<Choice> statesObservableList;
    private ObservableList<Choice> prioritiesObservableList;
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
    private ComboBox<Choice> taskState;

    @FXML
    private ComboBox<Choice> taskPriority;

    @FXML
    private TableView<Task> tasks;

    @FXML
    private TableColumn<Task, Integer> columnNr;

    @FXML
    private TableColumn<Task, String> columnShortDescription;

    @FXML
    private TableColumn<Task, Choice> columnState;

    @FXML
    private TableColumn<Task, String> columnPriority;


    @FXML
    private void initialize() {
        taskObservableList = FXCollections.observableList(ts.getTaskList());
        statesObservableList = FXCollections.observableArrayList(ts.getStateList());
        prioritiesObservableList = FXCollections.observableArrayList(ts.getPriorityList());
        /*
         * Setzten der Consumer Call Back functions um die observable List synchron zu halten.
         */
        ts.setAddTaskCallback(task -> Platform.runLater(() -> taskObservableList.add(task)));
        ts.setRemoveTaskCallback(task -> Platform.runLater(() -> taskObservableList.remove(task)));
        ts.setAddPriorityCallback(priority -> Platform.runLater(() -> prioritiesObservableList.add(priority)));
        ts.setRemovePriorityCallback(priority -> Platform.runLater(() -> prioritiesObservableList.remove(priority)));
        ts.setAddStateCallback(state -> Platform.runLater(() -> statesObservableList.add(state)));
        ts.setRemoveStateCallback(state -> Platform.runLater(() -> statesObservableList.remove(state)));

        taskState.setItems(statesObservableList);
        taskPriority.setItems(prioritiesObservableList);

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
        taskState.setValue(tc.getState());
        taskPriority.setValue(tc.getPriority());
        toggleTaskFormOn();
    }

    private boolean updateTaskFromTaskForm () {
        if (tc.getTask() != null) {
            tc.setShortDescription(taskShortDescription.getText());
            tc.setDescription(taskDescription.getText());
            tc.setPriority(taskPriority.getSelectionModel().getSelectedItem());
            tc.setState(taskState.getSelectionModel().getSelectedItem());
            return true;
        }

        return false;
    }


    public void initNewTask(ActionEvent actionEvent) {
        loadTaskIntoTaskForm(ts.createNewTask());
        statusLabel.setText(Localization.getTranslation("task-created"));
    }

    public void saveTask(ActionEvent actionEvent) {
        Task task = tc.getTask();
        if (updateTaskFromTaskForm()) {
            statusLabel.setText(Localization.getTranslation("task-saved"));
            ts.saveTask(task);
        }
    }

    public void deleteTask(ActionEvent actionEvent) {
        ts.removeTaskFromList(tc.getTask());
        statusLabel.setText(Localization.getTranslation("task-deleted"));
    }

    public void loadExistingTask(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            SelectionModel<Task> selectionModel = tasks.getSelectionModel();
            Task task = selectionModel.getSelectedItem();
            if (task != null) {
                loadTaskIntoTaskForm(ts.getTaskByNumber(task.getNumber()));
            }
        }
    }

    public void showSettings(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings-dialog.fxml"));
            fxmlLoader.setControllerFactory(c -> new SettingsGUIView(prioritiesObservableList, statesObservableList));
            Parent root = fxmlLoader.load();
            Dialog settingsDialog = new Dialog();
            settingsDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            settingsDialog.getDialogPane().setContent(root);
            settingsDialog.showAndWait();
            tasks.refresh();
            statesObservableList.sort((Choice state1, Choice state2) -> state1.getOrder() - state2.getOrder());
            prioritiesObservableList.sort((Choice priority1, Choice priority2) -> priority1.getOrder() - priority2.getOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
