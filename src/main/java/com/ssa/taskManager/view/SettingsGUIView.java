package com.ssa.taskManager.view;

import com.ssa.taskManager.controller.ChoiceController;
import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.repositories.ChoiceRepository;
import com.ssa.taskManager.service.TaskService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

public class SettingsGUIView {


    private ObservableList<Choice> prioritiesObservableList;
    private ObservableList<Choice> statesObservableList;
    private TaskService ts = TaskService.getInstance();
    private ChoiceController cc = new ChoiceController();

    @FXML
    public ChoiceBox<Choice> priorityDefaultValue;

    @FXML
    public ChoiceBox<Choice> stateDefaultValue;

    @FXML
    private TableView<Choice> priorities;

    @FXML
    private TableView<Choice> states;

    @FXML
    private TableColumn<Choice, String> columnPriorityValue;

    @FXML
    private TableColumn<Choice, Integer> columnPriorityOrder;


    @FXML
    private TableColumn<Choice, String> columnStateValue;

    @FXML
    private TableColumn<Choice, Integer> columnStateOrder;

    public SettingsGUIView(ObservableList<Choice> prioritiesObservableList, ObservableList<Choice> statesObservableList) {
        this.prioritiesObservableList = prioritiesObservableList;
        this.statesObservableList = statesObservableList;
    }


    @FXML
    private void initialize() {
        prepareColumns(columnPriorityValue, columnPriorityOrder);
        prepareColumns(columnStateValue, columnStateOrder);

        priorities.setRowFactory(param -> {
            TableRow<Choice> row = new TableRow<>();
            row.setOnMouseClicked(event -> createNewChoice(event, (TableRow<Choice>) event.getSource(), "priority"));
            return row;
        });

        states.setRowFactory(param -> {
            TableRow<Choice> row = new TableRow<>();
            row.setOnMouseClicked(event -> createNewChoice(event, (TableRow<Choice>) event.getSource(), "state"));
            return row;
        });


        priorityDefaultValue.setItems(prioritiesObservableList);
        stateDefaultValue.setItems(statesObservableList);

        priorityDefaultValue.setValue(prioritiesObservableList.stream().filter(priority -> priority.isDefaultValue()).findFirst().orElse(null));
        stateDefaultValue.setValue(statesObservableList.stream().filter(state -> state.isDefaultValue()).findFirst().orElse(null));

        priorityDefaultValue.setOnAction(event -> {
            ChoiceRepository.updateDefaultValue(priorityDefaultValue.getValue());
            ts.setDefaultPriority(priorityDefaultValue.getValue());
        });

        stateDefaultValue.setOnAction(event -> {
            ChoiceRepository.updateDefaultValue(stateDefaultValue.getValue());
            ts.setDefaultState(stateDefaultValue.getValue());
        });

        priorities.setEditable(true);
        states.setEditable(true);

        priorities.setItems(prioritiesObservableList);
        states.setItems(statesObservableList);
    }

    private void prepareColumns(TableColumn<Choice, String> columnValue, TableColumn<Choice, Integer> columnOrder) {
        columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnValue.setCellFactory(TextFieldTableCell.forTableColumn());
        columnOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
        columnOrder.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        columnValue.setOnEditCommit(event -> {
            TablePosition<Choice, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Choice choice = event.getTableView().getItems().get(row);
            cc.loadModel(choice);
            cc.setValue(newValue);
            ChoiceRepository.updateFieldValue(newValue, cc.getId());
        });

        columnOrder.setOnEditCommit(event -> {
            TablePosition<Choice, Integer> pos = event.getTablePosition();
            int newValue = event.getNewValue();
            int row = pos.getRow();
            Choice choice = event.getTableView().getItems().get(row);
            cc.loadModel(choice);
            cc.setOrder(newValue);
            ChoiceRepository.updateOrder(newValue, cc.getId());
        });
    }

    public void createNewChoice(MouseEvent mouseEvent, TableRow<Choice> row, String fieldName) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2 && row.isEmpty()) {
            cc.createChoice(fieldName);
            TableView<Choice> tableView = row.getTableView();
            row.getTableView().getItems().add(cc.getChoice());
            row.getTableView().edit(row.getIndex(), tableView.getColumns().get(0));
            ChoiceRepository.createNewChoice(cc.getChoice());
        }
    }
}
