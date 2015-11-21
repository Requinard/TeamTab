package Game;

import javassist.bytecode.stackmap.TypeData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Team {

    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    private final int STARTLIVES = 3;
    private final int STARTTIME = 9;
    private List<Player> players;
    private List<Panel> panels;
    private String name;
    private List<Instruction> activeInstructions;
    private int lives = STARTLIVES;
    private int time = STARTTIME;
    private int score;

    /**
     * @param name
     */
    public Team(String name) {
        this.name = name;
        this.score = 0;
        players = new ArrayList<Player>();
        panels = new ArrayList<Panel>();
    }

    /**
     * Author Qun
     * returns the players in the team
     *
     * @return List containing all players of the team
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Author Qun
     * returns all the panels the team has
     *
     * @return List of all panels a team has
     */
    public Collection<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Author Qun
     * returns the name of the team
     *
     * @return The name of the team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Author Qun
     * returns all active instructions the team has
     *
     * @return All active instructions of a team
     */
    public List<Instruction> getActiveInstructions() {
        return this.activeInstructions;
    }

    /**
     * Author Qun
     * returns the lives the team currently has
     *
     * @return The amount of lives the team has
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Author Qun
     * returns the time the team has per round
     *
     * @return The time the team has
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Author Qun
     * returns the current score of the team
     *
     * @return The score of the team
     */
    public int getScore() {
        return score;
    }


    /**
     * Checks if the list is empty or if the player is already in the team
     * if there is a team and the person is not in it, the player is added to the team
     * Author Qun, Kaj
     *
     * @param player
     */
    public Player addPlayer(Player player) {
        if (players.size() == 0 || !players.contains(player)) {
            players.add(player);
            player.setTeam(this);
            log.log(Level.INFO, "Player has been added to the team");
            return player;
        }
        log.log(Level.INFO, "Player has not been added to the team");
        player = null;
        return player;
    }


    /**
     * Removes player if it is in the team
     * Author Qun, Kaj
     *
     * @param player
     */
    public Player removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            log.log(Level.INFO, "Player is removed from the team");
        } else {
            player = null;
            log.log(Level.INFO, "Player is not removed from the team");
        }
        return player;
    }

    /**
     * This method generates panels for the team based on the amount of players
     * It can't contain the same panels.
     * Author Qun, Kaj
     *
     * @param gamePanels this is a list of panels gotten from the Game
     * @return panels       this is a list of panels that is given to a team
     */
    public List<Panel> generatePanels(List<Panel> gamePanels) {
        List<Panel> gamePanelsCopy = gamePanels;
        List<Panel> panelsGivenToPlayer;
        for (Player player : players) {
            panelsGivenToPlayer = player.generatePanels(gamePanels);
            gamePanelsCopy.removeAll(panelsGivenToPlayer);
        }
        return gamePanelsCopy;
    }

    /**
     * Change the lives by the amount of amount
     * Author Kaj
     *
     * @param amount amount may be positive or negative
     */
    public void changeLives(int amount) {
        lives += amount;
    }

    /**
     * Change the time by the amount of amount
     * Author Kaj
     *
     * @param amount amount may be positive or negative
     */
    public int changeTime(int amount) {
        return time += amount;
    }


    public boolean hasChanged() {
        // TODO - implement Team.hasChanged
        throw new UnsupportedOperationException();
    }

    /**
     * Author Qun
     * When the team doesn't have anymore lives, the team is not alive.
     *
     * @return True if the team is still alive, false if team has lost
     */
    public boolean isAlive() {
        if (getLives() <= 0) {
            log.log(Level.INFO, "Team is alive");
            return true;
        }
        log.log(Level.INFO, "Team is not alive");
        return false;
    }

    /**
     * Reset the data of the team
     * Author Frank Hartman
     *
     * @param hard if true the amount of lives will be set to the beginning amount of time
     */
    public void reset(boolean hard) {
        // If it is a hard reset then reset the lives
        if (hard)
            lives = STARTLIVES;

        time = STARTTIME;
        log.log(Level.INFO, String.format("Team: %s has been reset, hard reset = %s", name, hard));
    }

    /**
     * Checks if the panel belongs to a active instruction
     * if so, score + 1, remove the instruction from the active list and return true
     * Author Kaj
     * @param panel the pressed panel
     * @return true if the pressed panel was a active instruction
     */
    public boolean validateInstruction(Panel panel) {
        for (Instruction instruction : activeInstructions) {
            if (instruction.getPanel().equals(panel)) {
                score++;
                activeInstructions.remove(instruction);
                log.log(Level.INFO, "validating instruction ended, panel: {0} was correct", panel.getText());
                return true;
            }
        }
        log.log(Level.INFO, "validating instruction ended, panel: {0} was incorrect", panel.getText());
        return false;
    }
}