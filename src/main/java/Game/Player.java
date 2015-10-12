package Game;

import java.util.ArrayList;

/**
 * Created by Qun on 12-10-2015.
 */
public class Player {
    String ipAdress;
    String name;
    int score;
    ArrayList<Panel> panels;

    public Player() {
        panels = new ArrayList<Panel>();
    }
}
