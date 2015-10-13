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
    private Game game;
    private Team team;

    public Player(String ipAdress, String name, int score, ArrayList<Panel> panels, Game game, Team team) {
        this.panels = panels;
        this.ipAdress = ipAdress;
        this.name = name;
        this.score = score;
        this.game = game;
        this.team = team;
        //instructions = new ArrayList<Instruction>();
    }
    /*
    * This first creates a random
    * After that is creates a random number between 0 and the listlength
    * After that it selects the panel
    * */
    public Panel getPanel() {
        //random panels getten uit de lijst
        Random r = new Random();
        int random = r.nextInt(panels.size());
        return panels.get(random);

    }

    /*
    * Return the ip adress that is linked to the player
    **/

    public String getIpAdress() {
        return ipAdress;
    }

    /*
    * Return the name of the player
    **/
    public String getName(){
        return name;
    }

    /*
    * Returns the score of the current player
    **/
    public int getScore(){
        return score;
    }

    /*
     *return the game the player is assigned to
     **/

    public Game getGame(){
        return game;
    }

    /*
    * Returns the team the player is in
    **/
    public Team getTeam() {
        return team;
    }
}
