package main.model;

import main.service.TaskManagerService;
import main.resources.Priority;
import main.resources.State;
import main.util.OutputUtilities;

public class Task {
private int number;
private String shortDescription;
private String description;
private State state;
private Priority priority;

    public Task(int number) {
        this.number = number;
        TaskManagerService.getInstance().increaseTaskNumber();
    }

    public int getNumber() {
        return number;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state.getDisplayValue();
    }

    public void setState(int state) {

        this.state = State.OPEN;
        switch (state) {
            case 1:
                this.state = State.OPEN;
                break;
            case 2:
                this.state = State.IN_PROGRESS;
                break;
            case 3:
                this.state = State.DONE;
                break;
        }
    }

    public String getPriority() {
        return priority.getDisplayValue();
    }

    public void setPriority(int priority) {
        this.priority = Priority.LOW;
        switch (priority) {
            case 1:
                this.priority = Priority.HIGH;
                break;
            case 2:
                this.priority = Priority.MEDIUM;
                break;
            case 3:
                this.priority = Priority.LOW;
                break;
        }
    }
}
