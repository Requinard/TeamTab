package Game;

import networking.mediator.ClientMediator;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ClientGame implements IGame {
    private final double TICKRATE = 1;
    public LocalGame localGame = new LocalGame();
    //instruction of this player
    Thread mediatorThread;
    Timer timer;
    private ClientMediator mediator;
    private List<Team> teams = new LinkedList<>();
    private List<Player> players = new LinkedList<>();
    private List<Panel> panels = new LinkedList<>();

    private GameStateEnum gameState = GameStateEnum.LobbyView;

    public ClientGame(int portnumber) {
        localGame.setPlayer(null);
        localGame.setPanels(new ArrayList<>());
        mediator = new ClientMediator(this);
        teams = new ArrayList<>();
        panels = new ArrayList<>();
        players = new ArrayList<>();

        mediatorThread = mediator.mediate();
        loadPanelsFromFile();
    }

    public ClientGame() {
        this(8085);
    }

    public void setGameState(GameStateEnum gameState) {
        this.gameState = gameState;
    }

    public void scheduleRefresh() {
        timer = new Timer("Client Update");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };

        timer.schedule(task, 0, Math.round((1.0 / TICKRATE) * 1000));
    }

    /**
     * Author Frank Hartman
     * Gets the IP adress of the host
     *
     * @return the IP of the host
     */
    public String getHostIP() {
        return localGame.getHostIP();
    }

    /**
     * Author Frank Hartman
     * Gets the local ip of the pc
     *
     * @return IP of the client
     */
    public String getLocalIP() {
        return localGame.getLocalIP();
    }

    /**
     * Author Frank Hartman
     * Set the localip of the pc
     *
     * @param localIP IP of the client
     */
    public void setLocalIP(String localIP) {
        this.localGame.setLocalIP(localIP);
    }


    public void setHostIp(String hostIP) {
        this.localGame.setHostIP(hostIP);
    }

    /**
     * Author Kaj
     * gets all the players in the game
     *
     * @return the players in the game
     */
    @Override
    public synchronized List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Author Frank Hartman
     * Set the players in the game
     *
     * @param remotePlayers The players that will be set
     */
    public synchronized void setPlayers(List<Player> remotePlayers) {
        for (Player remotePlayer : remotePlayers) {
            if (localGame.getPlayer() != null && localGame.getPlayer().getUsername().equals(remotePlayer.getUsername())) {
                localGame.setPlayer(remotePlayer);
            }
        }
        players = remotePlayers;
    }

    /**
     * Author Kaj
     * gets all the teams in the game
     *
     * @return the teams in the game
     */
    @Override
    public synchronized List<Team> getTeams() {
        return this.teams;
    }

    /**
     * Author Frank Hartman
     * Set the teams in the game
     *
     * @param teams The teams that will be set
     */
    public synchronized void setTeams(List<Team> teams) {
        for (Team remoteTeam : teams) {
            Team team = this.getTeam(remoteTeam.getName());
            if (team == null) {
                this.teams.add(remoteTeam);
            }
        }
    }

    public void setTeams(HashMap<String, List<String>> map) {
        for (String teamName : map.keySet()) {
            Team team = getTeam(teamName);


            if (team != null) {
                team.getPlayers().clear();
                for (String playerName : map.get(teamName)) {
                    Player player = this.getPlayer(playerName);
                    if (player != null)
                        team.addPlayer(player);
                    if (localGame.getPlayer().getIp().equals(player.getIp())) {
                        localGame.setTeam(team);
                    }
                }
            }
        }
    }

    /**
     * Author Kaj
     * Gets all the panels in the game
     *
     * @return the panels of the game
     */
    @Override
    public List<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Author Kaj
     * sets the list of panels
     *
     * @param panels list of panels of the game
     */
    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    /**
     * Author Frank Hartman
     * Create a team in the game
     *
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
     *
     * @param username the username of the player
     * @param ip       the ip adres of the player
     */
    @Override
    public Player createPlayer(String username, String ip) {
        Player player = new Player(username, ip);
        mediator.createPlayer(player);
        localGame.setPlayer(player);
        gameState = GameStateEnum.LobbyView;
        return player;
    }

    /**
     * Author Frank Hartman
     * Assign a player to a team in the game
     *
     * @param player the player that will be assigned to the team
     * @param team   the team that will get the player
     */
    @Override
    public void assignTeam(Player player, Team team) {
        mediator.assignTeam(team);
    }

    /**
     * Author Frank Hartman
     *
     * @param hard if true than hardreset
     */
    @Override
    public void reset(boolean hard) {

    }

    /**
     * Author Kaj
     * not necessary
     *
     * @return null not necessary
     */
    @Override
    public HostGame startRound() {
        return null;
    }

    /**
     * Author Frank Hartman
     *
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
     *
     * @param instruction the instruction that was to late
     */
    @Override
    public void registerInvalidInstruction(Instruction instruction) {
        instruction = localGame.getPlayer().getActiveInstruction();
        if (instruction != null)
            mediator.registerInvalidInstruction(instruction);
    }

    /**
     * Updates the game through the mediator
     * Authir: David
     */
    public void update() {
        if (gameState == GameStateEnum.LobbyView) {
            mediator.getPlayers();
            mediator.getTeams();
            mediator.getTeamAssignments();
        } else if (gameState == GameStateEnum.GameView) {
            mediator.getPanels();
            mediator.getTeams();
        } else if (gameState == GameStateEnum.ScoreView) {
            mediator.getInstructions();
        }
    }

    public void setLocalPlayer(Player localPlayer) {
        localGame.setPlayer(localPlayer);
    }

    /**
     * Author Kaj
     * change the status of the player to show that he is ready to start the game
     *
     * @param playerStatus true if the player is ready to start the game
     */
    public void changePlayerStatus(boolean playerStatus) {
        localGame.getPlayer().setPlayerStatus(playerStatus);
        mediator.setPlayerStatus(localGame.getPlayer());
    }

    public Team getTeam(String teamName) {
        for (Team team : this.teams) {
            if (team.getName().equals(teamName)) return team;
        }
        return null;
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getUsername().equals(playerName)) return player;
        }
        return null;
    }

    public void stopSchedule() {
        timer.cancel();
        timer.purge();
    }

    private boolean loadPanelsFromFile() {
        try (InputStream location = this.getClass().getClassLoader().getResourceAsStream("panels.csv")) {
            String full = IOUtils.toString(location);

            // go over each line
            for (String s : full.split("\n")) {
                // If we start with a hashtag the line is commented and we skip it
                if (s.startsWith("#") || s.equals("\r"))
                    continue;
                // get the individual lines
                String[] split = s.split(",");

                // Parse the strings
                int id = Integer.parseInt(split[0].trim());
                int type = Integer.parseInt(split[1].trim());
                String text = split[2];
                int min = Integer.parseInt(split[3].trim());
                int max = Integer.parseInt(split[4].trim());

                // Create the panels
                Panel panel = new Panel(id, min, max, text, PanelTypeEnum.values()[type]);

                panels.add(panel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return panels.size() > 0;
    }
}