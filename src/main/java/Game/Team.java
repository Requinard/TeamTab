package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private String name;

    /**
     * @param time                  Time a player of this team has to complete a instruction
     * @param lives                 Amount of lives
     * @param correctInstructions   Amount of correct instructions in total
     * @param name                  name of the team
     */
    public Team(int time, int lives, int correctInstructions, String name){
        this.time =time;
        this.startTime = time;
        this.lives = lives;
        this.correctInstruction =correctInstructions;
        this.name = name;
        players = new ArrayList<>();
        playerPanels = new ArrayList<>();
    }

    /**
     * Gets the name of the team
     * @return team name
     */
    public String getName() {return this.name; }

    /**
     * Set name of the team
     * @param name
     */
    public void setName(String name) {this.name = name;}

    /**
     * Gets the current time for a instruction
     * @return the time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Sets the time for a instruction
     * @param time set the time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets the amount of correct instructions
     * @return the amount of correct instructions
     */
    public int getCorrectInstruction() {
        return this.correctInstruction;
    }

    /**
     * Set the amount of correct instructions
     * @param correctInstruction the new amount of correct instructions
     */
    public void setCorrectInstruction(int correctInstruction) {
        this.correctInstruction = correctInstruction;
    }

    /**
     * Gets the amount of lives from a team
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
     * @return All the panels from the team
     */
    public ArrayList<Panel> getPlayerPanels() {
        return this.playerPanels;
    }

    /**
     * Sets all the panels from a team
     * @param playerPanels The new list of player panels
     */
    public void setPlayerPanels(ArrayList<Panel> playerPanels) {
        this.playerPanels = playerPanels;
    }

    /**
     * Gives player 12 random panels from all the panels available to his team.
     * Gets all new panel after a one game round is finished (So every round has new panels)
     * @param allPanelsForTeam  list of all available panels one team
     * @return true when all new panels are assigned
     * @Author Qun
     */
    public void givePanelsToPlayersFromTeam(ArrayList<Panel> panelsTeam){
        ArrayList<Panel> tempPanels = new ArrayList<Panel>();
        Panel pan;
        Random r = new Random();
        this.playerPanels = allPanelsForTeam;

        // Check if there are enough panels
        if(allPanelsForTeam.size() < 12) {
            return false;
        }
        // Loop through the players
        for(Player player : players)
        {
            for(Panel panel : panelsTeam){
                //if(player.getPanels().size() < 12)
            for (int i = 0 ; i <12 ; i++)
            {
                pan = allPanelsForTeam.get(r.nextInt(allPanelsForTeam.size()));

                if(tempPanels.contains(pan))
                {
                    tempPanels.remove(r);
                    i--;
                }
                else {
                    tempPanels.add(pan);
                }
            }
            //Nu krijgt elke speler dezelfde panels. De eerste 12.
            player.setPanels(tempPanels);
            newInstruction(player);
        }
    return true;
    }

    /**
     * Get all the players that are in the team
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

    /**
     * If the list doesn't contains the player then this player will be added to this ArrayList<Player>
     * @param player
     * @return true when player is added to the list
     */
    public boolean addPlayerToTeam(Player player){
        //For the fist player
        if (players.size() == 0){
            players.add(player);
            player.setTeam(this);
            return true;
        }
        //All other players
        for(Player p : players){
            if(!p.equals(player)){
                players.add(player);
                player.setTeam(this);
                return true;
            }
        }
        return  false;
    }

    /**
     * If this list containt the player then the player will be removed
     * @param player
     * @return true when player is removed from the list
     * @Author Qun
     */
    public boolean removePlayer(Player player){
        for(Player p : players){
            if (p.equals(player)) {
                players.remove(player);
                return true;
            }
        }
        return false;
    }

    /**
     * sets all the variables to their default values and clears playerPanels List
     * @return true when all the values are checked for their default values and the above mentioned list are empty
     * @author Frank Hartman
     */
    public boolean resetTeam(){
        time = startTime;
        correctInstruction = 0;
        playerPanels.clear();
        return true;
    }

    /**
     * calls the comparable method on the Players list and sorts them DESCENDING
     * @return the unmodifiableList with the sorted players
     * @author Frank Hartman
     */
    public List<Player> sortedPlayerByScore(){
        List<Player> sortedPlayers = new ArrayList<>();
        Collections.sort(sortedPlayers);
        return Collections.unmodifiableList(sortedPlayers);
    }

    /**
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

    /**
     * decrease the lives of the times with 1
     * @author Frank Hartman
     * @return true if it succeeded
     */
    public boolean substractLives() {
        if(getTime() <= 3) {
            //moet dit niet setlives zijn
            this.lives--;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check if the given panel was one of the players instructions
     * If correct increase the score and gives a new instruction
     * If false reset correct instructions and gives new instruction
     * @param changedPanel  the panel that needs to be checked
     * @param sliderValue   the value of the panel
     * @return true if this was one of the instruction
     * @author Frank Hartman
     */
    public boolean checkTeamInstruction(Panel changedPanel, int sliderValue) {
        for (Player p : players) {

            // Check if the panel belongs to the player
            if (p.getInstruction().getPanel().equals(changedPanel)) {
                // Check if the value is correct
                if (p.checkCorrectPanel(changedPanel, sliderValue)) {
                    correctInstruction++;
                    p.addScore();
                    // Give the player a new instruction
                    newInstruction(p);
                    return true;
                }

                else {
                    //correctInstruction = 0;
                    // Give the player a new instruction
                    newInstruction(p);
                    return false;
                }
            }
            //correctInstruction = 0;
            // Give the player a new instruction
            newInstruction(p);
            return false;
        }
        return false;
    }

    /**
     * Give the player a new instruction
     * @param player The player that gets a new instruction
     */
    private void newInstruction(Player player) {
        // Give the player a new instruction
        Instruction playerInstruction = player.getInstruction();
        playerInstruction.createNewInstruction(player.getPanels(), playerInstruction);
    }

    /**
     * Add instruction time to the team
     * Time will only be added if the current amount of time is less the the max time
     * @param time      the extra amount of time
     * @param maxTime   the maximum amount of time
     * @author Frank Hartman
     */
    public boolean addTeamTime(int time, int maxTime) {
        if (time <= maxTime) {
            this.time = this.time + time;
            if (this.time > maxTime )
                this.time = maxTime;
            return true;
        }
        else
            return false;
    }

    /**
     * decreases time for instructions when called
     */
    public void decreaseTime() {
        this.time--;
    }
}
