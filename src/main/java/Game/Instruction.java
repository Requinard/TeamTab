package Game;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    Panel panel;
    String commando;
    int newValue;

    @Override
    public String toString() {
        return "Voer het commando : " + commando + " uit ";
    }
}
