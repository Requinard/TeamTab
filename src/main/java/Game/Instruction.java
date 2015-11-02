package Game;

import java.util.ArrayList;
import java.util.Random;

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
     * Gets the panel from the player
     * @return the panel
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * sets panel for the player
     * @param panel
     */
    public void setPanel(Panel panel) {
        this.panel = panel;
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

    /**
     * Change the current instruction to a new instruction
     * @param playerPanels The panels of the player that should get a new instruction
     * @author Frank Hartman
     */
    public boolean createNewInstruction(ArrayList<Panel> playerPanels) {
        Random random = new Random();
        try {

            this.panel = playerPanels.get(random.nextInt(playerPanels.size()));
            this.commando = "Change to";
            this.value = random.nextInt((panel.getMax() - panel.getMin()) + 1) + panel.getMin();
            // EERSTE ITERATIE

            System.out.println("Player gets a new commando");
            System.out.println(panel.getText() + ": " + commando + ": " + value);
            return true;
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
