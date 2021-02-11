package com.ssa.taskManager.view;

import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.repositories.ChoiceRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettingsGUIView {

    private ObservableList<Choice> prioritiesObservableList = FXCollections.observableArrayList(ChoiceRepository.getPriorities());
    private ObservableList<Choice> statesObservableList = FXCollections.observableArrayList(ChoiceRepository.getStates());

    @FXML
    private TableView priorities;

    @FXML
    private TableView states;

    @FXML
    private TableColumn columnPriorityValue;

    @FXML
    private TableColumn columnPriorityOrder;

    @FXML
    private TableColumn columnPriorityDefaultValue;

    @FXML
    private TableColumn columnStateValue;

    @FXML
    private TableColumn columnStateOrder;

    @FXML
    private TableColumn columnStateDefaultValue;

    @FXML
    private void initialize() {
        columnPriorityValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnPriorityOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
        columnPriorityDefaultValue.setCellValueFactory(new PropertyValueFactory<>("defaultValue"));

        columnStateValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnStateOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
        columnStateDefaultValue.setCellValueFactory(new PropertyValueFactory<>("defaultValue"));


        priorities.setItems(prioritiesObservableList);
        states.setItems(statesObservableList);
    }
}
