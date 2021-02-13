package com.ssa.taskManager.view;

import com.ssa.taskManager.model.Choice;
import com.ssa.taskManager.model.Priority;
import com.ssa.taskManager.model.State;
import com.ssa.taskManager.repositories.ChoiceRepository;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class SettingsGUIView {

    private ObservableList<Choice> prioritiesObservableList = FXCollections.observableArrayList(ChoiceRepository.getPriorities());
    private ObservableList<Choice> statesObservableList = FXCollections.observableArrayList(ChoiceRepository.getStates());

    @FXML
    private TableView<Choice> priorities;

    @FXML
    private TableView<Choice> states;

    @FXML
    private TableColumn<Choice, String> columnPriorityValue;

    @FXML
    private TableColumn<Choice, Integer> columnPriorityOrder;

    @FXML
    private TableColumn<Choice, Boolean> columnPriorityDefaultValue;

    @FXML
    private TableColumn<Choice, String> columnStateValue;

    @FXML
    private TableColumn<Choice, Integer> columnStateOrder;

    @FXML
    private TableColumn<Choice, Boolean> columnStateDefaultValue;

    @FXML
    private void initialize() {
        columnPriorityValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnPriorityOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
        columnPriorityDefaultValue.setCellValueFactory(param -> checkBoxCellValueFactoryCallback(param));
        columnPriorityDefaultValue.setCellFactory(param ->  checkBoxCellFactoryCallback());

        columnStateValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        columnStateOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
        columnStateDefaultValue.setCellValueFactory(param -> checkBoxCellValueFactoryCallback(param));
        columnStateDefaultValue.setCellFactory(param ->  checkBoxCellFactoryCallback());

        priorities.setEditable(true);
        states.setEditable(true);

        priorities.setItems(prioritiesObservableList);
        states.setItems(statesObservableList);
    }


    private CheckBoxTableCell<Choice, Boolean> checkBoxCellFactoryCallback() {
            CheckBoxTableCell<Choice, Boolean> cell = new CheckBoxTableCell<Choice, Boolean>();
            cell.setAlignment(Pos.CENTER);
            return cell;
    }

    private BooleanProperty checkBoxCellValueFactoryCallback(TableColumn.CellDataFeatures<Choice, Boolean> param) {
        Choice choice = param.getValue();

        SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(choice.isDefaultValue());

        booleanProp.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                Boolean newValue) {
                choice.setDefaultValue(newValue);
            }
        });
        return booleanProp;
    }
}
