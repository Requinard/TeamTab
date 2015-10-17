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
    * @Returns the panel which is linked to the instruction
    * */
    public Panel getPanel() {
        return panel;
    }

    /**
     * @Returns the commando the player has to execute
     */

    public String getCommando() {
        return commando;
    }

    /**
    * @Returns the value of the instruction
    * it checks if it is true or false
     */
    public int getNewValue() {return newValue;}




    @Override
    public String toString() {
        return "Voer het commando : " + commando + " uit ";
    }


}
