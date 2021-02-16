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
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

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
        Dialog <List<Pair<String, Integer>>> settingsDialog = new Dialog<>();
        settingsDialog.setTitle(Localization.getLabels().getString("settings"));
        settingsDialog.setHeaderText(Localization.getLabels().getString("settings-header"));
        DialogPane dialogPane = new DialogPane();

        ButtonType saveButtonType = new ButtonType((Localization.getLabels().getString("save")), ButtonBar.ButtonData.OK_DONE);
        settingsDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox taskState = new ComboBox();
        ComboBox taskPriority = new ComboBox();

        taskState.setItems(statesObservableList);
        taskPriority.setItems(prioritiesObservableList);

        taskState.setValue(Localization.getLabels().getString(ts.getDefaultState().name()));
        taskPriority.setValue(Localization.getLabels().getString(ts.getDefaultPriority().name()));

        grid.add(new Label(Localization.getLabels().getString("task-priority-label")), 0, 0);
        grid.add(taskPriority, 1, 0);
        grid.add(new Label(Localization.getLabels().getString("task-state-label")), 0, 1);
        grid.add(taskState, 1, 1);

        settingsDialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        settingsDialog.setResultConverter(dialogButton -> {
            List <Pair<String, Integer>> settings = new ArrayList<>();
            if (dialogButton == saveButtonType) {
                settings.add(new Pair<>("priority", taskPriority.getSelectionModel().getSelectedIndex()));
                settings.add(new Pair<>("state", taskState.getSelectionModel().getSelectedIndex()));
                return settings;
            }
            return settings;
        });

        Optional<List<Pair<String, Integer>>> result = settingsDialog.showAndWait();
        result.ifPresent(settings -> {
            settings.forEach(setting -> {
                System.out.println(setting.getKey() + ": " + setting.getValue());
            });
        });
    }
}
