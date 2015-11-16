package Game;

public class Instruction {

    private Panel panel;
    private int intendedValue;
    private boolean wasExecutedCorrectly = false;

    public Instruction(Panel panel, int intendedValue) {
        this.panel = panel;
        this.intendedValue = intendedValue;
    }

    public Panel getPanel() {
        return this.panel;
    }

    public int getIntendedValue() {
        return this.intendedValue;
    }

    public boolean getWasExecutedCorrectly() {
        return this.wasExecutedCorrectly;
    }

    public void setWasExecutedCorrectly(boolean wasExecutedCorrectly) {
        this.wasExecutedCorrectly = wasExecutedCorrectly;
    }
}