package Game;

import networking.mediator.ClientMediator;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class ClientGame implements IGame {
    public Player localPlayer;
    //instruction of this player
    public Instruction localInstruction;
    Thread mediatorThread;
    private ClientMediator mediator;
    private List<Team> teams;
    private List<Player> players;
    private List<Panel> panels;
    private String hostIP;

    public ClientGame(int portnumber) {
        localPlayer = null;
        mediator = new ClientMediator(this);
        teams = new ArrayList<>();
        panels = new ArrayList<>();
        players = new ArrayList<>();

        mediatorThread = mediator.mediate();
    }

    public ClientGame() {
        this(8085);
    }

    /**
     * Author Frank Hartman
     * Gets the IP adress of the host
     *
     * @return the IP of the host
     */
    public String getHostIP() {
        return hostIP;
    }

    public void setHostIp(String hostIP) {
        this.hostIP = hostIP;
    }

    /**
     * Author Kaj
     * gets all the players in the game
     * @return the players in the game
     */
    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Author Frank Hartman
     * Set the players in the game
     * @param players The players that will be set
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
        System.out.println("fsfasfafs");
    }

    /**
     * Author Kaj
     * gets all the teams in the game
     * @return the teams in the game
     */
    @Override
    public List<Team> getTeams() {
        return this.teams;
    }

    /**
     * Author Frank Hartman
     * Set the teams in the game
     * @param teams The teams that will be set
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Author Kaj
     * Gets all the panels in the game
     * @return the panels of the game
     */
    @Override
    public List<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Author Kaj
     * sets the list of panels
     * @param panels list of panels of the game
     */
    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    /**
     * Author Frank Hartman
     * Create a team in the game
     * @param name the name of the team
     * @return The team that was created
     */
    @Override
    public Team createTeam(String name) throws IllegalFormatException {
        Team team = new Team(name);
        mediator.createTeam(team);
        return team;
    }

    /**
     * Author Kaj
     * create a player in the game
     * @param username the username of the player
     * @param ip       the ip adres of the player
     */
    @Override
    public Player createPlayer(String username, String ip) {
        Player player = new Player(username, ip);
        mediator.createPlayer(player);
        return player;
    }

    /**
     * Author Frank Hartman
     * Assign a player to a team in the game
     * @param player the player that will be assigned to the team
     * @param team   the team that will get the player
     */
    @Override
    public void assignTeam(Player player, Team team) {
        mediator.assignTeam(team);
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
     * not necessary
     * @return null not necessary
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
        mediator.processPanel(panel);
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
     * Register a invalid instruction when the player is running out of time
     * @param instruction the instruction that was to late
     */
    @Override
    public void registerInvalidInstruction(Instruction instruction) {
        instruction = localPlayer.getActiveInstruction();
        mediator.registerInvalidInstruction(instruction);
    }


    /**
     * Author Kaj
     * change the status of the player to show that he is ready to start the game
     * @param playerStatus true if the player is ready to start the game
     */
    public void changePlayerStatus(boolean playerStatus) {
        localPlayer.setPlayerStatus(playerStatus);
        mediator.setPlayerStatus(localPlayer);
    }
}