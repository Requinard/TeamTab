package game;

import java.util.Collection;
import java.util.List;

public class Game {

    private Collection<Player> players;
    private Collection<Team> teams;
    private Collection<Panel> panels;

    public Game() {
        // TODO - implement Game.Game
        throw new UnsupportedOperationException();
    }

    public Collection<Player> getPlayers() {
        return this.players;
    }

    public Collection<Team> getTeams() {
        return this.teams;
    }

    public Collection<Panel> getPanels() {
        return this.panels;
    }

    /**
     * @param name
     */
    public Team createTeam(String name) {
        // TODO - implement Game.createTeam
        throw new UnsupportedOperationException();
    }

    /**
     * @param username
     * @param ip
     */
    public Player createPlayer(String username, String ip) {
        // TODO - implement Game.createPlayer
        throw new UnsupportedOperationException();
    }

    /**
     * @param player
     * @param team
     */
    public void assignTeam(Player player, Team team) {
        // TODO - implement Game.assignTeam
        throw new UnsupportedOperationException();
    }

    private List<Panel> generatePanels() {
        // TODO - implement Game.generatePanels
        throw new UnsupportedOperationException();
    }

    private void loadPanelsFromFile() {
        // TODO - implement Game.loadPanelsFromFile
        throw new UnsupportedOperationException();
    }

    /**
     * @param hard
     */
    public void reset(boolean hard) {
        // TODO - implement Game.reset
        throw new UnsupportedOperationException();
    }

    /**
     * @param player
     * @param panel
     */
    private boolean validateInstruction(Player player, Panel panel) {
        // TODO - implement Game.validateInstruction
        throw new UnsupportedOperationException();
    }

    public Game startRound() {
        // TODO - implement Game.startRound
        throw new UnsupportedOperationException();
    }

    /**
     * @param player
     * @param panel
     */
    public boolean processPanel(Player player, Panel panel) {
        // TODO - implement Game.processPanel
        throw new UnsupportedOperationException();
    }

}