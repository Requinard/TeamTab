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

    public List<Player> getPlayers() {
        return this.players;
    }

    public Collection<Panel> getPanels() {
        return this.panels;
    }

    public String getName() {
        return this.name;
    }

    public List<Instruction> getActiveInstructions() {
        return this.activeInstructions;
    }

    public int getLives() {
        return this.lives;
    }

    public int getTime() {
        return this.time;
    }

    public int getScore() {
        return score;
    }

    /**
     * Checks if the list is empty or if the player is already in the team
     * if there is a team and the person is not in it, the player is added to the team
     * Author Qun, Kaj
     * @param player
     */
    public Player addPlayer(Player player) {
        if (players.size() == 0 || !players.contains(player)) {
            players.add(player);
            player.setTeam(this);
            return player;
        }
        player = null;
        return player;
    }

    /**
     * Removes player if it is in the team
     * Author Qun, Kaj
     * @param player
     */
    public Player removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
        return player;
    }

    /**
     * This method generates panels for the team based on the amount of players
     * It can't contain the same panels.
     * Author Qun, Kaj
     * @param gamePanels this is a list of panels gotten from the Game
     * @return panels       this is a list of panels that is given to a team
     */
    public List<Panel> generatePanels(List<Panel> gamePanels) {
        for (Player player : players) {
            List<Panel> panels = player.generatePanels(gamePanels);
            gamePanels.removeAll(panels);
        }
        return panels;
    }

    /**
     * Change the lives by the amount of amount
     * Author Kaj
     * @param amount    amount may be positive or negative
     */
    public void changeLives(int amount) {
        lives += amount;
    }

    /**
     * Change the time by the amount of amount
     * Author Kaj
     * @param amount    amount may be positive or negative
     */
    public int changeTime(int amount) {
        return time += amount;
    }

    public boolean hasChanged() {
        // TODO - implement Team.hasChanged
        throw new UnsupportedOperationException();
    }

	public boolean isAlive() {
		// TODO - implement Team.isAlive
		throw new UnsupportedOperationException();
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
        log.log(Level.INFO, "Team: " + name + " has been reset, hard reset = " + hard);
    }

    /**
     * Changes the activeInstructions and score
     * Author Kaj
     *
     * @param instruction the instruction that was correctly preformed
     */
    public void correctInstructionPreformed(Instruction instruction) {
        score++;
        //activeInstructions.remove(instruction);
    }


}