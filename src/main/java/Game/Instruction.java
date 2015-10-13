package Game;

import java.util.ArrayList;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    private Panel panel;
    private String commando;
    private int newValue;
    private ArrayList<Instruction> instructions;

    public Instruction(Panel Panel, String Commando, int NewValue) {
        instructions = new ArrayList<Instruction>();
        this.panel = Panel;
        this.commando = Commando;
        this.newValue = NewValue;
    }

    /*
    * Returns the panel which*/
    public Panel getPanel() {
        return panel;
    }
    public String getCommando() {
        return commando;
    }
    public int getNewValue() {return newValue;}

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }


    @Override
    public String toString() {
        return "Voer het commando : " + commando + " uit ";
    }


}
