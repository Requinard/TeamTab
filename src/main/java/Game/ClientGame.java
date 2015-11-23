package Game;

import networking.mediator.ClientMediator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.List;

public class ClientGame implements IGame {
    public Player localePlayer;

    private ClientMediator clientMediator;
    private List<Team> teams;
    private List<Player> players;
    private List<Panel> panels;
    private String hostIP;

    public ClientGame() {
        localePlayer = null;
        clientMediator = new ClientMediator(this);
        teams = new ArrayList<>();
    }

    /**
     * Author Frank Hartman
     *
     * @return
     */
    public String getHostIP() {
        return hostIP;
    }

    /**
     * Author Kaj
     * @return
     */
    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    /**
     * Author Frank Hartman
     * @param players
     */
    public void setPlayers(List<Player> players) {
        throw new NotImplementedException();
    }

    /**
     * Author Kaj
     */
    @Override
    public Collection<Team> getTeams() {
        return this.teams;
    }

    /**
     * Author Frank Hartman
     * @param teams
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Author Kaj
     */
    @Override
    public Collection<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Author Frank Hartman
     * @param name the name of the team
     */
    @Override
    public Team createTeam(String name) throws IllegalFormatException {
        Team team = new Team(name);
        clientMediator.createTeam(team);
        return team;
    }

    /**
     * Author Kaj
     * @param username the username of the player
     * @param ip       the ip adres of the player
     */
    @Override
    public Player createPlayer(String username, String ip) {
        Player player = new Player(username, ip);
        clientMediator.createPlayer(player);
        return player;
    }

    /**
     * Author Frank Hartman
     * @param player the player that will be assigned to the team
     * @param team   the team that will get the player
     */
    @Override
    public void assignTeam(Player player, Team team) {
        clientMediator.assignTeam(team);
    }

    /**
     * Author Frank Hartman
     * @param hard if true than hardreset
     */
    @Override
    public void reset(boolean hard) {

    }

    /**
     * Author Kaj
     * @return
     */
    @Override
    public HostGame startRound() {
        return null;
    }

    /**
     * Author Frank Hartman
     * @param player the player of which the panel has been changed
     * @param panel  the panel that is pressed
     */
    @Override
    public boolean processPanel(Player player, Panel panel) {
        clientMediator.processPanel(panel);
        return false;
    }

    /**
     * Author Kaj
     * Check if the game has ended
     * The game has been ended when all the teams, except for one have a zero amount of lives
     * Author Frank Hartman
     *
     * @return true if the game has ended
     */
    @Override()
    public boolean hasGameEnded() {
        int count = 0;

        for (Team team : teams) {
            if (team.getLives() <= 0)
                count++;
        }

        return count >= teams.size() - 1;

    }

    /**
     * Author Frank Hartman
     * @param instruction the instruction that was to late
     */
    @Override
    public void registerInvalidInstruction(Instruction instruction) {
        clientMediator.registerInvalidInstruction(instruction);
    }


    /**
     * Author Kaj
     *
     * @param playerStatus
     */
    public void changePlayerStatus(boolean playerStatus) {
        localePlayer.setPlayerStatus(playerStatus);
        clientMediator.setPlayerStatus(localePlayer);
    }
}