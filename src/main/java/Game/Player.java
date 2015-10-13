package Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Qun on 12-10-2015.
 */
public class Player {
    private String ipAdress;
    private String name;
    private int score;
    private ArrayList<Panel> panels;
    private Instruction instructions;

    public Player(String Ipadress, String Name, int Score, ArrayList<Panel> Panel) {
        this.panels = Panel;
        this.ipAdress = Ipadress;
        this.name = Name;
        this.score = Score;

        //instructions = new ArrayList<Instruction>();
    }

    public void getPanel() {
        //random panels getten uit de lijst
        Random r = new Random();
        int random = r.nextInt(panels.size());
        panels.get(random);

    }

    public String getipAdress() {
        return ipAdress;
    }

    public String name(){
        return name;
    }
    public int score(){
        return score;
    }


}
