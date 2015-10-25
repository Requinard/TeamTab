package Game;

import java.util.ArrayList;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    private Panel panel;
    private String commando;
    private int newValue;

    public Instruction(Panel panel, String commando, int newValue) {
        this.panel = panel;
        this.commando = commando;
        this.newValue = newValue;
    }

    /**
     * sets panel for the player
     * @param panel
     */
    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    /**
     * Gets the panel from the player
     * @return the panel
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * Returns the command the player has to execute
     * @return the commando
     */
    public String getCommando() {
        return commando;
    }


    /**
     * Returns the value of the instruction
     * @return instruction value
     */
    public int getNewValue() {return newValue;}


    /**
     * Returns the commando the player has to execute
     * @author Frank Hartman
     * @return the tostring commando
     */
    @Override
    public String toString() {
        if (panel != null)
            return commando + " : " + panel.getText();
        else
            return commando;
    }


}
