package Game;

import java.util.ArrayList;

/**
 * Created by HP user on 12-10-2015.
 */
public class Team {
    private ArrayList<Player> players;
    private ArrayList<Panel> playerPanels;

    private int time;
    private int lives;
    private int correctInstruction;

    //get

    public ArrayList<Player> getPlayers() {return players;}




    public Team(){
        time = 9;
        lives = 3;
        correctInstruction = 0;
        players = new ArrayList<Player>();
        playerPanels = new ArrayList<Panel>();
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCorrectInstruction() {
        return correctInstruction;
    }

    public void setCorrectInstruction(int correctInstruction) {
        this.correctInstruction = correctInstruction;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public ArrayList<Panel> getPlayerPanels() {
        return playerPanels;
    }

    public void setPlayerPanels(ArrayList<Panel> playerPanels) {
        this.playerPanels = playerPanels;
    }
}
