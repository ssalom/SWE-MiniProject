package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.TaskController;
import com.ssa.taskManager.model.Priority;
import com.ssa.taskManager.model.State;
import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.service.TaskService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class TaskManagerGUIView {
    private TaskService ts = TaskService.getInstance();
    private ObservableList<Task> taskObservableList;
    private TaskController tc;

    @FXML
    private AnchorPane taskForm;

    @FXML
    private MenuItem newTask;

    @FXML
    private MenuItem settings;

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

    private void toggleTaskFormOn () {
        if (taskForm.isDisable()) {
            taskForm.setDisable(false);
        }
    }

    private void toggleTaskFormOff () {
        if (!taskForm.isDisable()) {
            taskForm.setDisable(true);
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

    private void clearTaskForm () {
        taskNr.setText("");
        taskShortDescription.setText("");
        taskDescription.setText("");
        getChoiceLists(1, 1);
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

    private void getChoiceLists (int stateIndex, int priorityIndex) {
        ObservableList states = FXCollections.observableArrayList(Arrays.asList(State.values()));
        ObservableList priorities = FXCollections.observableArrayList(Arrays.asList(Priority.values()));
        taskState.setItems(states);;
        taskState.getSelectionModel().select(stateIndex);
        taskPriority.setItems(priorities);
        taskPriority.getSelectionModel().select(priorityIndex);
    }

    public void initNewTask(ActionEvent actionEvent) {
        clearTaskForm();
        loadTaskIntoTaskForm(ts.createNewTask());
    }

    public void saveTask(ActionEvent actionEvent) {
        Task task = tc.getTask();
        if (updateTaskFromTaskForm()) {
            ts.saveTask(task);
        }
    }

    public void deleteTask(ActionEvent actionEvent) {
        ts.removeTaskFromList(tc.getTask());
    }

    public void loadExistingTask(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            SelectionModel<Task> selectionModel = tasks.getSelectionModel();
            Task task = selectionModel.getSelectedItem();
            loadTaskIntoTaskForm(ts.getTaskByNumber(task.getNumber()));
        }
    }
}
