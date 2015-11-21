package Game;

import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientGame implements IGame {

    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    private List<Player> players;
    private List<Team> teams;
    private List<Panel> panels;

    public ClientGame() {
        players = new ArrayList<Player>();
        teams = new ArrayList<Team>();
        panels = new ArrayList<Panel>();

        loadPanelsFromFile();
    }

    public List<Player> getPlayers() {
        return this.players;
	}

    public List<Team> getTeams() {
        return this.teams;
	}

    public List<Panel> getPanels() {
        return this.panels;
	}

	/**
     * Create a team and add this team to the teams in the game
     * Author Frank Hartman
     * @param name the name of the team
     */
    @Override
    public Team createTeam(String name) throws UnsupportedOperationException {
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
     * Create a player and add this player to the players in the game
     * Author Frank Hartman
     * @param username The username of the player
     * @param ip The ip address of the player
     */
    @Override
	public Player createPlayer(String username, String ip) {
        //check if username is empty
        if (username == null || username.isEmpty()) {
            throw new UnsupportedOperationException("name of the user is empty");
        }
        //check if ip is empty
        if (ip == null || ip.isEmpty()) {
            throw new UnsupportedOperationException("ip address the is empty");
        }
        Player player = new Player(username, ip);
        // Add the player to the players in the game
        players.add(player);
        log.log(Level.INFO, "player: " + username + " added to list players");
        return player;
    }

	/**
     * Assign a player to a team in the game and removes the player from his current team if he is in one
     * @param player The player that wants to be assigned to a team
     * @param team The team that the player wants to join
     */
    @Override
	public void assignTeam(Player player, Team team) {
        for(Team currentTeam: teams) {
            if(currentTeam.getPlayers().contains(player)) {
                currentTeam.removePlayer(player);
            }
        }
        team.addPlayer(player);
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
        log.log(Level.INFO,"Started loading panels");
        URL location = this.getClass().getClassLoader().getResource("panels.csv");

        try (FileInputStream fileInputStream = new FileInputStream(location.getPath().replace("%20", " "))) {
            String full = IOUtils.toString(fileInputStream);
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
            log.log(Level.INFO, "There are {0} panels added from the CSV file",String.valueOf(panels.size()));
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE,e.toString(),e);
        }

        return panels.size() > 0;
    }

	/**
	 * Resets lives, time and instructions for all teams.
     * Author Frank Hartman
     * @param hard Indicates that this is a hard reset. Destroy all data
	 */
	@Override()
	public void reset(boolean hard) {

        log.log(Level.INFO, "Resetting teams");
        for (Team team : teams) {
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
    public ClientGame startRound() {
        // first load all the panels from the CSV file into the list of panels
        loadPanelsFromFile();
        // iterate over all teams
        for (Team team : teams) {
            // give a team first a hard reset
            team.reset(true);
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
     * @param player    player that pressed a panel
     * @param panel     The pressed panel
     * @return true if the pressed panel was correct
     */
	@Override
	public boolean processPanel(Player player, Panel panel) {
        boolean correctInstruction;
        //check if the pressed panel was from an active instruction
        correctInstruction = validateInstruction(player, panel);
        if (correctInstruction) {
            //possible list of correct instruction saved?
            //new instruction for the player that had the active instruction
        }
        return correctInstruction;
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
     * Takes an instruction and marks it as invalid, thus generating a new instruction for a player
     *
     * @param instruction The instruction that needs to be marked as invalid     *
     */
    @Override
	public boolean registerInvalidInstruction(Instruction instruction) {
        throw new UnsupportedOperationException();
    }

	/**
	 * Takes an instruction and asserts whether the click was a valid instruction in your team.
     * If correct, than the instruction will be removed from the list of active instruction from the team.
     * If correct, score of team will go up by 1
     * Author Kaj
     * @param player Player that clicked on a panel
	 * @param panel Panel control that was clicked
     * @return true is the panel had a active instruction
     */
    private boolean validateInstruction(Player player, Panel panel) {
        boolean correctInstruction = false;
        Team team = player.getTeam();
        //Get all active instructions from the players team
        for (Instruction instruction : team.getActiveInstructions()) {
            //check if the panel matches an active instruction
            if (instruction.getPanel() == panel) {
                correctInstruction = true;
                //the correct instruction will be removed from the active instructions list
                //the score will get plus 1
                team.correctInstructionPreformed(instruction);
                log.log(Level.INFO, "Panel was correct");
                return correctInstruction;
            }
        }
        log.log(Level.INFO, "Panel was incorrect");
        return correctInstruction;
    }

}