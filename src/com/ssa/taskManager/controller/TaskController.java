package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.resources.enums.Priority;
import com.ssa.taskManager.resources.enums.State;
import com.ssa.taskManager.utilities.Localization;
import com.ssa.taskManager.view.TaskView;

import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private Task model;
    private TaskView view = new TaskView();

    public TaskController(Task model) {
        this.model = model;
    }

    public Task getTask () {
        return model;
    }

    public String getNumber () {
        return String.format("%03d", model.getNumber());
    }

    public String getShortDescription() {
        return model.getShortDescription();
    }

    public void setShortDescription(String shortDescription) {
        model.setShortDescription(shortDescription);
    }

    public String getDescription() {
        return model.getDescription();
    }

    public void setDescription(String description) {
        model.setDescription(description);
    }

    public String getState() {
        return Localization.getLabels().getString(model.getState().name());
    }

    public void setState(int state) {
        state -= 1;
        if (state == State.IN_PROGRESS.ordinal()) {
            model.setState(State.IN_PROGRESS);
        } else if (state == State.DONE.ordinal()) {
            model.setState(State.DONE);
        } else if (state == State.OPEN.ordinal()) {
            model.setState(State.OPEN);
        }
    }

    public String getPriority() {
        return Localization.getLabels().getString(model.getPriority().name());
    }

    public void setPriority(int priority) {
        priority -= 1;
        if (priority == Priority.MEDIUM.ordinal()) {
            model.setPriority(Priority.MEDIUM);
        } else if (priority == Priority.HIGH.ordinal()) {
            model.setPriority(Priority.HIGH);
        } else {
            model.setPriority(Priority.LOW);
        }
    }

    public void printOutTaskDetails () {
        view.printOutTaskDetails(getNumber(), getShortDescription(), getDescription(), getState(), getPriority());
    }

    public List<String> getTaskColumnForlist () {
        List<String> col = new ArrayList<>();

        col.add(getNumber());
        col.add(getShortDescription());
        col.add(getState());
        col.add(getPriority());

        return col;
    }

}
