package main.resources;

public enum State {
    OPEN(1, "Open"),
    IN_PROGRESS(2, "In Progress"),
    DONE(3, "Done");

    private int value;
    private String displayValue;

    private State(int stateCode, String displayValue) {
        this.value = stateCode;
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
