package Game;

import gui.panel.AbstractPanelControl;
import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class ClientGame implements IGame {

    private List<Player> players;
    private List<Team> teams;
    private List<Panel> panels;
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    public ClientGame() {
        players = new ArrayList<Player>();
        teams = new ArrayList<Team>();
        panels = new ArrayList<Panel>();
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
        if (name == null || name.isEmpty()) {
            throw new UnsupportedOperationException("name of the is empty");
        }
        Team team = new Team(name);
        // Add the team to the teams in the game
        teams.add(team);
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
        Player player = new Player(username, ip);
        // Add the player to the players in the game
        players.add(player);
        return player;
    }

	/**
     * Assign a player to a team in the game
     * @param player The player that wants to be assigned to a team
     * @param team The team that the player wants to join
     */
    @Override
	public void assignTeam(Player player, Team team) {
        team.addPlayer(player);
    }

	private java.util.List<Panel> generatePanels() {
        throw new UnsupportedOperationException();
    }

    /**
     * Load panels from CSV
     *
     * @return true if panels are correctly loaded
     */
    private boolean loadPanelsFromFile() {
        URL location = this.getClass().getClassLoader().getResource("panels.csv");

        try (FileInputStream fileInputStream = new FileInputStream(location.getPath().replace("%20", " "))) {
            String full = IOUtils.toString(fileInputStream);

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

	@Override
	public ClientGame startRound() {
        throw new UnsupportedOperationException();
    }

	/**
	 * 
	 * @param player
	 * @param panel
	 */
	@Override
	public boolean processPanel(Player player, Panel panel) {
        throw new UnsupportedOperationException();
    }

	@Override()
	public boolean hasGameEnded() {
		// TODO - implement ClientGame.hasGameEnded
		throw new UnsupportedOperationException();
	}

	/**
	 * Takes an instruction and marks it as invalid, thus generating a new instruction for a player
	 * @param instruction The instruction that needs to be marked as invalid
	 */
	@Override
	public boolean registerInvalidInstruction(Instruction instruction) {
        throw new UnsupportedOperationException();
    }

	/**
	 * Takes an instruction and asserts whether the click was a valid instruction in your team.
	 * @param player Player that clicked on a panel
	 * @param panel Panel control that was clicked
	 */
	private boolean validateInstruction(Player player, AbstractPanelControl panel) {
        throw new UnsupportedOperationException();
    }

}