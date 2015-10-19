package Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Qun on 12-10-2015.
 */
public class Player implements Comparable<Player>{
    private String ipAdress;
    private String name;
    private int score;
    private ArrayList<Panel> panels;
    private Instruction instructions;
    private Game game;
    private Team team;

    public Player(String ipAdress, String name, int score, ArrayList<Panel> panels, Instruction instruction, Game game, Team team) {
        this.ipAdress = ipAdress;
        this.name = name;
        this.score = score;
        this.panels = panels;
        this.instructions = instruction;
        this.game = game;
        this.team = team;
        //instructions = new ArrayList<Instruction>();
    }

    /**
     * @return IpAdress of the user
     */
    public String getIpAdress() {
        return this.ipAdress;
    }

    /**
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }


    /**
     *
     * @return the score of the current player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @return the game the player is assigned to
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * @return the instructions for the player
     */
    public Instruction getInstructions() {
        return this.instructions;
    }

    /**
     *
     * @param instructions Sets the instructions for the player
     */
    public void setInstructions(Instruction instructions) {
        this.instructions = instructions;
    }

    /**
     *
     * @return  the team the player is in
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     *
     * @param team Sets the team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     *
     * @return the panels from the player
     */
    public ArrayList<Panel> getPanels() {
        return this.panels;
    }

    /**
     *
     * @param panels Sets the panels
     */
    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    /**
     * @param panel
     * @return true or false
     * Check if the panel that is given is correct
     */
    public boolean checkCorrectPanel(Panel panel) {
        if (instructions.getPanel().equals(panel)) {
            return true;
        }
        else
            return false;
    }


    /**
     * this is the implementation of the Comparable interface. Based on the DESCENDING compare
     * @param p player
     * @return
     */
    public int compareTo(Player p) {
        int compareScore = ((Player)p).getScore();
        return compareScore - this.score;
    }
}
