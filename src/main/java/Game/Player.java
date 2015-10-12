package Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Qun on 12-10-2015.
 */
public class Player {
    String ipAdress;
    String name;
    int score;
    ArrayList<Panel> panels;
    ArrayList<Instruction> instructions;


    public Player() {
        panels = new ArrayList<Panel>();
        instructions = new ArrayList<Instruction>();
    }

    public void getPanel() {
        //random panels getten uit de lijst
        Random r = new Random();
        int random = r.nextInt(panels.size());
        panels.get(random);

    }

}
