package Game;

public class Instruction {

    private Panel panel;
    private int intendedValue;
    private boolean wasExecutedCorrectly = false;

    /**
     * Constructor of a instruction
     * author Frank Hartman
     *
     * @param panel         The panel of the instruction
     * @param intendedValue The value of the instruction
     */
    public Instruction(Panel panel, int intendedValue) {
        this.panel = panel;
        this.intendedValue = intendedValue;
    }

    /**
     * Get the panel of the instruction
     * author Frank Hartman
     * @return the panel
     */
    public Panel getPanel() {
        return this.panel;
    }

    /**
     * Return the value of the instruction
     * author Frank Hartman
     * @return The intendedValue
     */
    public int getIntendedValue() {
        return this.intendedValue;
    }

    /**
     * Check if the instruction was executed correctly
     * author Frank Hartman
     * @return true if the instruction was executed correctly
     */
    public boolean getWasExecutedCorrectly() {
        return this.wasExecutedCorrectly;
    }

    /**
     * Set the value of the executed instruction
     * author Frank Hartman
     * @param wasExecutedCorrectly True if the instruction was executed correctly
     */
    public void setWasExecutedCorrectly(boolean wasExecutedCorrectly) {
        this.wasExecutedCorrectly = wasExecutedCorrectly;
    }
}