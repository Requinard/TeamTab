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
     * Returns IP adress of the player
     * @return the IP adress of the player
     */
    public String getIpAdress() {
        return this.ipAdress;
    }

    /**
     * Returns name of the player
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the score of the player
     * @return the players score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Return the game of the player
     * @return the game of the player
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Return the instructions assigned to the player
     * @return the instructions
     */
    public Instruction getInstruction() {
        return this.instructions;
    }

    /**
     * Sets the instructions for the player
     * @param instructions
     */
    public void setInstructions(Instruction instructions) {
        this.instructions = instructions;
    }

    /**
     * Returns the team of the player
     * @return the player team
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Sets the team of the player
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns the player his panels
     * @return a list of panels for the player
     */
    public ArrayList<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Sets the panels for the player
     * @param panels
     */
    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    /**
     * @author Frank Hartman
     * add a point to the score of the player
     */
    public void addScore() {
        score++;
    }

    /**
     * @Author Qun & Frank
     * Check value of the instruction and compares it to the current value of the panel
     * Check if the panel is correct
     * @param panel
     * @return true of false
     */
    public boolean checkCorrectPanel(Panel panel) {
        if (instructions.getValue() == (panel.getCurrent())) {
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

    @Override
    public String toString() {
        return  "Player: " + name;
    }
}
