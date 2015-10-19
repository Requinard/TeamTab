package Game;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HP user on 12-10-2015.
 */
public class Team {

    private ArrayList<Player> players;
    private ArrayList<Panel> playerPanels;


    private int time;
    private int startTime;

    private int lives;
    private int correctInstruction;


    public Team(int time, int lives){
        this.time =time;
        this.startTime = time;
        this.lives = lives;
        this.correctInstruction = 0;
        players = new ArrayList<Player>();
        playerPanels = new ArrayList<Panel>();
    }


    /**
     * Gets the current time for a instruction
     * @author Frank Hartman
     * @return the time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Sets the time for a instruction
     * @author Frank Hartman
     * @param time set the time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets the amount of correct instructions
     * @author Frank Hartman
     * @return the amount of correct instructions
     */
    public int getCorrectInstruction() {
        return this.correctInstruction;
    }

    /**
     * Set the amount of correct instructions
     * @author Frank Hartman
     * @param correctInstruction the new amount of correct instructions
     */
    public void setCorrectInstruction(int correctInstruction) {
        this.correctInstruction = correctInstruction;
    }

    /**
     * Gets the amount of lives from a team
     * @author Frank Hartman
     * @return the amount of lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Set the amount of lives
     * @param lives the new amount of lives
     */

    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Get all the panels from a team
     * @author Frank Hartman
     * @return All the panels from the team
     */
    public ArrayList<Panel> getPlayerPanels() {
        return this.playerPanels;
    }

    /**
     * Sets all the panels from a team
     * @author Frank Hartman
     * @param playerPanels The new list of player panels
     */
    public void setPlayerPanels(ArrayList<Panel> playerPanels) {
        this.playerPanels = playerPanels;
    }

    /**
     * Get all the players that are in the team
     * @author Frank Hartman
     * @return all the players from the team
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Set all new players
     * @param players the list with players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /***
     * If the list doesn't contains the player then this player will be added to this ArrayList<Player>
     * @param player
     * @return true when player is added to the list
     */
    public boolean addPlayerToTeam(Player player){
        if (players.size() == 0){
            players.add(player);
            refreshPlayerPanels();
            return true;
        }
        for(Player p : players){ //eerst add voor het conroleren
            if(!p.equals(player)){
                players.add(player);
                refreshPlayerPanels();
                return true;
            }
        }
        return  false;
    }

    /***
     * If this list containt the player then the player will be removed
     * @param player
     * @return true when player is removed from the list
     */
    public boolean removePlayer(Player player){
        for(Player p : players){
            if(p.equals(player)){
                players.remove(player);
                refreshPlayerPanels();
                return true;
            }
        }
        return false;
    }

    /***
     * sets all the variables to their default values and clears the Player & playerPanels List
     * @author Frank Hartman
     * @return true when all the values are checked for their default values and the above mentioned list's are empty
     */
    public boolean resetTeam(){
        time = startTime;
        correctInstruction = 0;
        players.clear();
        playerPanels.clear();

        if(time == 9 && correctInstruction == 0 && players.size() == 0 && playerPanels.size() == 0){
            return true;
        }
        return false;
    }

    /***
     * calls the comparable method on the Players list and sorts them DESCENDING
     * @author Frank Hartman
     * @return the unmodifiableList with the sorted players
     */
    public List<Player> sortedPlayerByScore(){
        List<Player> sortedPlayers = new ArrayList<Player>(players);
        Collections.sort(sortedPlayers);
        return Collections.unmodifiableList(sortedPlayers);
    }

    /***
     * Checks if the player is already part of the team.
     * @param player
     * @return true if the player is in the team
     */
    public boolean isPlayerInTeam(Player player){

        for(Player p : players ){
            if(p.equals(player)){
                return true;
            }
        }
        return false;
    }

    private void refreshPlayerPanels() {
        ArrayList<Panel> tempPanels = new ArrayList<Panel>();


        for (Player player : players) {
            for (Panel p : player.getPanels()) {
                tempPanels.add(p);
            }
        }
        playerPanels = tempPanels;
    }


}
