package com.ssa.taskManager.model;

import com.ssa.taskManager.resources.enums.Priority;
import com.ssa.taskManager.resources.enums.State;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private int number;
    private String shortDescription;
    private String description;
    private State state;
    private Priority priority;
    private AtomicInteger nextNumber = new AtomicInteger(1);

    public Task() {
        this.number = nextNumber.getAndIncrement();
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
