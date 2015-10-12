package Game;

import java.util.ArrayList;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    private Panel panel;
    String commando;
    int newValue;
    ArrayList<Instruction> instructions;

    public Instruction() {
        instructions = new ArrayList<Instruction>();
    }




    @Override
    public String toString() {
        return "Voer het commando : " + commando + " uit ";
    }

    public Panel getPanel() {
        return panel;
    }
}
