package main.resources;

public enum Priority {
    HIGH(1, "Hoch"),
    MEDIUM(2, "Mittel"),
    LOW(3, "Niedrig");

    private int value;
    private String displayValue;

    private Priority(int value, String displayValue) {
        this.value = value;
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
