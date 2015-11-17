package Game;

import java.util.List;
import java.util.Random;

public class Player {

    private String username;
    private String ip = "127.0.0.1";
    private List<Panel> panels;
    private Team team;
    private Instruction activeInstruction;

    /**
     * Constructor of the player
     * author Frank Hartman
     * @param username the username of the player
     * @param ip the ip address of the player
     */
    public Player(String username, String ip) {
        this.username = username;
        this.ip = ip;

    }

    /**
     * Get the username of the player
     * author Frank Hartman
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the username of the player
     * author Frank Hartman
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the IP of the player
     * author Frank Hartman
     * @return the ip of the player
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Get the panels of the player
     * author Frank Hartman
     * @return the panels of the player
     */
    public List<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Set the panels of the player
     * author Frank Hartman
     */
    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    /**
     * Get the team of the player
     * author Frank Hartman
     * @return
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Set the team of the player
     * @param team the team of the player
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Give the active instruction of the player
     * @return The instruction of the player
     */
    public Instruction getActiveInstruction() {
        return this.activeInstruction;
    }

    /**
     * This method generates random panels for a player. A player gets 12 random panels
     * author Qunfong
     * @param teamPanels Panels which are given for a team
     * @return Panels that have been added to a player his panels
     */
    public java.util.List<Panel> generatePanels(List<Panel> teamPanels) {
        Random random = new Random();
        final int PANELSPERPLAYER = 12;
        for (int i = 0; i <= PANELSPERPLAYER; i++) {
            //Gets random panel from teamPanels and adds this to the players panel
            panels.add(teamPanels.get(random.nextInt(teamPanels.size())));
        }
        return panels;
    }

    /**
     * Check if the player currently has a panel
     * @param panel
	 */
    public boolean hasPanel(Panel panel) {
        // TODO - implement Player.hasPanel
        throw new UnsupportedOperationException();
    }

    public Instruction generateInstruction() {

        // TODO - implement Player.generateInstruction
        throw new UnsupportedOperationException();
    }

}