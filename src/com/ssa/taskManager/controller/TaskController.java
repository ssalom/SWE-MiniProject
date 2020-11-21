package com.ssa.taskManager.controller;

import com.ssa.taskManager.model.Task;
import com.ssa.taskManager.resources.enums.Priority;
import com.ssa.taskManager.resources.enums.State;
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

    public State getState() {
        return model.getState();
    }

    public void setState(int state) {
        if (state == State.IN_PROGRESS.ordinal()) {
            model.setState(State.IN_PROGRESS);
        } else if (state == State.DONE.ordinal()) {
            model.setState(State.DONE);
        } else if (state == State.OPEN.ordinal()) {
            model.setState(State.OPEN);
        }
    }

    public Priority getPriority() {
        return model.getPriority();
    }

    public void setPriority(int priority) {
        if (priority == Priority.LOW.ordinal()) {
          model.setPriority(Priority.LOW);
        } else if (priority == Priority.MEDIUM.ordinal()) {
            model.setPriority(Priority.MEDIUM);
        } else if (priority == Priority.HIGH.ordinal()) {
            model.setPriority(Priority.HIGH);
        }
    }

    public void printOutTaskDetails () {
        view.printOutTaskDetails(getNumber(), getShortDescription(), getDescription(), getState().name(), getPriority().name());
    }

    public List<String> getTaskColumnForlist () {
        List<String> col = new ArrayList<>();

        col.add(getNumber());
        col.add(getShortDescription());
        col.add(getState().name());
        col.add(getPriority().name());

        return col;
    }

}
