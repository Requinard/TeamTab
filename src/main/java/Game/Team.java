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
    private int lives;
    private int correctInstruction;


    public Team(int time, int lives, int correctInstructions){
        this.time =time;
        this.lives = lives;
        this.correctInstruction =correctInstructions;
        players = new ArrayList<Player>();
        playerPanels = new ArrayList<Panel>();
    }


    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCorrectInstruction() {
        return this.correctInstruction;
    }

    public void setCorrectInstruction(int correctInstruction) {
        this.correctInstruction = correctInstruction;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public ArrayList<Panel> getPlayerPanels() {
        return this.playerPanels;
    }

    public void setPlayerPanels(ArrayList<Panel> playerPanels) {
        this.playerPanels = playerPanels;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

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
     * @return true when all the values are checked for their default values and the above mentioned list's are empty
     */
    public boolean resetTeam(){
        time = 9;
        correctInstruction = 0;
        players.clear();
        playerPanels.clear();

        if(time == 9 && lives == 3 && correctInstruction == 0 && players.size() == 0 && playerPanels.size() == 0){
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
        List<Player> sortedPlayers = new ArrayList<>(players);
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
