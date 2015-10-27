package Game;

import java.util.ArrayList;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    private Panel panel;
    private String commando;
    private int value;

    public Instruction(Panel panel, String commando, int value) {
        this.panel = panel;
        this.commando = commando;
        this.value = value;
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
    public int getValue() {return value;}


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
