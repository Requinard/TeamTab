package game;

import java.util.Collection;
import java.util.List;

public class Team {

    private Player players;
    private Collection<Panel> panels;
    private String name;
    private List<Instruction> activeInstructions;
    private int lives = 3;
    private int time = 9;

    /**
     * @param name
     */
    public Team(int name) {
        // TODO - implement Team.Team
        throw new UnsupportedOperationException();
    }

    public Player getPlayers() {
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

    /**
     * @param player
     */
    public Player addPlayer(Player player) {
        // TODO - implement Team.addPlayer
        throw new UnsupportedOperationException();
    }

    /**
     * @param player
     */
    public Player removePlayer(Player player) {
        // TODO - implement Team.removePlayer
        throw new UnsupportedOperationException();
    }

    public List<Panel> generatePanels() {
        // TODO - implement Team.generatePanels
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     */
    public void changeLives(int amount) {
        // TODO - implement Team.changeLives
        throw new UnsupportedOperationException();
    }

    /**
     * @param amount
     */
    public int changeTime(int amount) {
        // TODO - implement Team.changeTime
        throw new UnsupportedOperationException();
    }

    public boolean hasChanged() {
        // TODO - implement Team.hasChanged
        throw new UnsupportedOperationException();
    }

}