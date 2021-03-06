package Game;

import networking.mediator.HostMediator;
import networking.mediator.IMediator;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HostGame implements IGame {

    private static final Logger log = Logger.getLogger(HostGame.class.getName());
    private List<Player> players;
    private List<Team> teams;
    private List<Panel> panels;
    private List<Instruction> instructions;
    private IMediator mediator;
    private Thread mediatorThread;

    public HostGame() {
        this(8085);
    }

    public HostGame(int port) {
        players = new ArrayList<>();
        teams = new ArrayList<>();
        panels = new ArrayList<>();
        instructions = new ArrayList<Instruction>();

        loadPanelsFromFile();

        mediator = new HostMediator(this, port);

        mediatorThread = mediator.mediate();
    }

    public List<Player> getPlayers() {
        return this.players;
    }


    public List<Instruction> getInstructions() {
        return instructions;
    }

    public synchronized List<Team> getTeams() {
        return this.teams;
    }

    /**
     * Gets all panels of the game
     * These are all the panels loaded from the CSV
     * Author Kaj
     *
     * @return the list of panels
     */
    public List<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Create a team and add this team to the teams in the game
     * Author Frank Hartman
     *
     * @param name the name of the team
     */
    @Override
    public synchronized Team createTeam(String name) throws UnsupportedOperationException {
        //check if name is empty
        if (name == null || name.isEmpty()) {
            throw new UnsupportedOperationException("name of the team is empty");
        }
        Team team = new Team(name);
        // Add the team to the teams in the game
        teams.add(team);
        log.log(Level.INFO, "team: " + name + " added to list teams");
        return team;
    }

    /**
     * Author Qun
     * made this method so when you want to change the teamname
     * it resets the teams so it can create new teams
     */
    public void resetTeamName() {
        teams.clear();
    }

    /**
     * Create a player and add this player to the players in the game
     * Author Frank Hartman
     *
     * @param username The username of the player
     * @param ip       The ip address of the player
     */
    @Override
    public synchronized Player createPlayer(String username, String ip) {
        //check if username is empty
        if (username == null || username.isEmpty()) {
            throw new UnsupportedOperationException("name of the user is empty");
        }
        //check if ip is empty
        if (ip == null || ip.isEmpty()) {
            throw new UnsupportedOperationException("ip address the is empty");
        }
        Player player = new Player(username, ip);
        autoAssignTeam(player);
        // Add the player to the players in the game
        players.add(player);
        System.out.println(username + "  " + ip);
        log.log(Level.INFO, "player: " + username + " added to list players");
        return player;
    }

    /**
     * Assign a player to a team in the game and removes the player from his current team if he is in one
     *
     * @param player The player that wants to be assigned to a team
     * @param team   The team that the player wants to join
     */
    @Override
    public synchronized void assignTeam(Player player, Team team) {
        log.log(Level.INFO, "assigning player: {0} to team started", player.getUsername());
        for (Team gameTeam : teams) {
            if (gameTeam.getName().equals(team.getName())) {
                team.addPlayer(player);
            } else {
                gameTeam.removePlayer(player);
            }
        }
        log.log(Level.INFO, "assign player to team ended, Player assigned to team: {0}", team.getName());
    }

    /**
     * Give the loaded panels to every team in the game
     * author Frank Hartman
     *
     * @return The list of panels in the game
     */
    private java.util.List<Panel> generatePanels() {
        for (Team team : teams) {
            team.generatePanels(panels);
        }
        return panels;
    }

    /**
     * Load panels from CSV
     *
     * @return true if panels are correctly loaded
     */
    private boolean loadPanelsFromFile() {
        log.log(Level.INFO, "Started loading panels");


        try (InputStream location = this.getClass().getClassLoader().getResourceAsStream("panels.csv")) {
            String full = IOUtils.toString(location);
            log.log(Level.INFO, "loaded panels from {0}", location.toString());

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
                log.log(Level.FINER, "Added panel with text {0}", panel.getText());
            }
            log.log(Level.INFO, "There are {0} panels added from the CSV file", String.valueOf(panels.size()));
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, e.toString(), e);
        }

        return panels.size() > 0;
    }

    /**
     * Resets lives, time and instructions for all teams.
     * Author Frank Hartman
     *
     * @param hard Indicates that this is a hard reset. Destroy all data
     */
    @Override()
    public void reset(boolean hard) {
        log.log(Level.INFO, "Resetting teams");
        for (Team team : teams) {
            System.out.println("Reseting teamssize: " + teams.size());
            team.reset(hard);
        }
    }


    /**
     * Start a new round by loading all the panels from the CSV file and give this panels to the team
     * The team will the distribute the panels to the players
     * Author Kamil Wasylkiewicz
     *
     * @return
     */
    @Override
    public HostGame startRound() {

        if (players.size() <= 0) {
            return null;
        }

        for (Player player : players) {
            if (!player.getPlayerStatus())
                return null;
        }

        // iterate over all teams
        for (Team team : teams) {
            // give a team first a hard reset
            team.reset(false);
            // let the team give their players new panels
            team.generatePanels(panels);
        }
        log.log(Level.INFO, "Round is started");
        return this;
    }

    /**
     * this method will first check if the given panel matches one of the active instructions for the team of the player
     * this will be done with te validateInstruction method
     * If the instruction was correct a new instruction must be given to the player that had the active instruction.
     * Author Kaj
     * Author Frank Hartman
     *
     * @param player player that pressed a panel
     * @param panel  The pressed panel
     * @return true if the pressed panel was correct
     */
    @Override
    public synchronized Instruction processPanel(Player player, Panel panel) {
        Instruction currentInstruction;
        Instruction newInstruction = null;
        //check if the pressed panel was from an active instruction
        currentInstruction = validateInstruction(player, panel);
        if (currentInstruction != null) {
            //check if there is a win streak
            changeTimeForTeam(player.getTeam(), 0);
            //list of correct instructions
            currentInstruction.setWasExecutedCorrectly(true);
            instructions.add(currentInstruction);
            //gives the player that had the instruction (not necessarily the one that pressed the panel) a new instruction
            newInstruction = player.getTeam().generateInstructionForPlayer(currentInstruction.getPlayer());
            return newInstruction;
        } else {
            changeTimeForTeam(player.getTeam(), -1);
        }

        return newInstruction;// != null;
    }

    /**
     * Check if the game has ended
     * The game has been ended when all the teams, except for one have a zero amount of lives
     * Author Frank Hartman
     *
     * @return true if the game has ended
     */
    @Override()
    public boolean hasGameEnded() {
        log.log(Level.INFO, "Check if the game has ended");
        int count = 0;

        for (Team team : teams) {
            if (team.getLives() <= 0)
                count++;
        }

        return count >= teams.size() - 1;

    }

    /**
     * Author Kamil Wasylkiewicz
     * Author Frank Hartman
     * Takes an instruction and marks it as invalid, thus generating a new instruction for a player
     *
     * @param instruction The instruction that needs to be marked as invalid
     * @return true if instruction belongs to player and generates a new instruction
     */
    @Override
    public synchronized Instruction registerInvalidInstruction(Instruction instruction) {
        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                if (player.getActiveInstruction().getPanel().getId() == instruction.getPanel().getId()) {
                    instruction = player.getActiveInstruction();
                }
            }
        }
        log.log(Level.INFO, "registering invalid Instruction for " + instruction.getPlayer().getUsername() + " ,panel name " + instruction.getPanel().getText());
        Player player = instruction.getPlayer();
        Team team = player.getTeam();
        // The instruction was not executed correctly and will be set to false
        instruction.setWasExecutedCorrectly(false);
        instructions.add(instruction);
        changeTimeForTeam(team, -1);
        // Generate new instruction for the player
        return team.generateInstructionForPlayer(player);
    }

    /**
     * Takes an instruction and asserts whether the click was a valid instruction in your team.
     * If correct, true will be returned
     * Author Kaj
     *
     * @param player Player that clicked on a panel
     * @param panel  Panel control that was clicked
     * @return instruction that was correctly performed, else null
     */
    private synchronized Instruction validateInstruction(Player player, Panel panel) {
        log.log(Level.INFO, "validating instruction for panel: {0} started", panel.getText());
        return player.getTeam().validateInstruction(panel);
    }

    /**
     * Change the time for a team and check the amount of time
     * Author Frank Hartman
     *
     * @param team The team that gets a different amount of time
     * @param time The time amount of time
     * @return true if the given team lost a life
     */
    private synchronized boolean changeTimeForTeam(Team team, int time) {
        log.log(Level.INFO, "staring chang time for team");
        team.changeTime(time);
        //win streak of 3, your team gets one second plus
        if (team.getScore() % 3 == 0 && team.getTime() < 9 && team.getScore() != 0) {
            team.changeTime(1);
            log.log(Level.INFO, "win streak of 3");
        }
        //win streak of 5, every other team gets minus one second
        if (team.getScore() % 5 == 0 && team.getScore() != 0) {
            //for all other teams
            for (Team team1 : teams) {
                if (!team1.equals(team)) {
                    team1.changeTime(-1);
                }
            }
            log.log(Level.INFO, "win streak of 5");
        }

        for (Team team1 : teams) {
            if (team1.getTime() <= 3) {
                team1.changeLives(-1);
                // Start a new round when the
                startRound();
                return true;
            }
        }
        return false;
    }

    /**
     * Gives a list with the score of every player in the game
     * Author Frank Hartman
     *
     * @return A list with the players and there score
     */
    public List<String> getScoreboard() {
        List<String> scoreboard = new ArrayList<>();

        for (Player player : players) {
            int playerScore = 0;
            if (instructions.isEmpty()) {
                scoreboard.add("Team: " + player.getTeam().getName());
                scoreboard.add(player.getUsername() + " Score: " + playerScore);
                return scoreboard;
            }
            for (Instruction instruction : instructions) {
                // Increase the score of the player if the instruction was executed correctly
                if (instruction.getPlayer().equals(player) && instruction.getWasExecutedCorrectly())
                    playerScore++;
                else if (instruction.getPlayer().equals(player) && !instruction.getWasExecutedCorrectly())
                    playerScore--;
            }
            // Add the team with the player and his score to the list
            scoreboard.add("Team: " + player.getTeam().getName() + " Name- " + player.getUsername() + " Score: " + playerScore);
        }

        return scoreboard;
    }

    public void autoAssignTeam(Player player) {
        if (teams.get(0).getPlayers().size() < teams.get(1).getPlayers().size()) {
            teams.get(0).addPlayer(player);
            for (Player p : players) {
                if (p == player) {
                    p.setTeam(teams.get(0));
                }
            }
        } else {
            teams.get(1).addPlayer(player);
            for (Player p : players) {
                if (p == player) {
                    p.setTeam(teams.get(1));
                }
            }
        }
    }

    //gets player by IP
    public Player getPlayer(String ipadress) {
        Player returnPlayer = null;
        for (Player player : players) {
            if (player.getIp().equals(ipadress))
                returnPlayer = player;
        }
        return returnPlayer;
    }
}