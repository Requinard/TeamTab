package Game;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    private static final Logger log = Logger.getLogger(Player.class.getName());
    private String username;
    private String ip;
    private transient List<Panel> panels = new LinkedList<>();
    private transient Team team;
    private Instruction activeInstruction;
    private boolean playerStatus = false;

    /**
     * Constructor of the player
     * author Frank Hartman
     *
     * @param username the username of the player
     * @param ip       the ip address of the player
     */
    public Player(String username, String ip) {
        this.username = username;
        this.ip = ip;
        panels = new ArrayList<>();
    }

    /**
     * Author Qun
     * gets the playerstatus from the player
     *
     * @return
     */
    public boolean getPlayerStatus() {
        return playerStatus;
    }

    /**
     * Set the status of the player
     * Author Frank Hartman
     */
    public synchronized void setPlayerStatus(boolean playerStatus) {
        this.playerStatus = playerStatus;
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
     *
     * @return the ip of the player
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Get the panels of the player
     * author Frank Hartman
     *
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
     *
     * @return the team of the player
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Set the team of the player
     *
     * @param team the team of the player
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Give the active instruction of the player
     *
     * @return The instruction of the player
     */
    public Instruction getActiveInstruction() {
        return this.activeInstruction;
    }

    public void setActiveInstruction(Instruction activeInstruction) {
        this.activeInstruction = activeInstruction;
    }

    /**
     * This method generates random panels for a player. A player gets 12 random panels
     * author Qunfong, Kaj
     *
     * @param teamPanels Panels which are given for a team
     * @return Panels that have been added to a player his panels
     */
    public java.util.List<Panel> generatePanels(List<Panel> teamPanels) {
        this.panels.clear();
        final int PANELSPERPLAYER = 12;
        Random random = new Random();
        //throws a exception if there are to few panels to assign
        if (teamPanels.size() < PANELSPERPLAYER) {
            throw new UnsupportedOperationException("Not enough panels to assign to a player");
        }

        for (int i = 0; i < PANELSPERPLAYER; i++) {
            //Gets random panel from teamPanels and adds this to the players panel
            Panel randomSelectedPanel = teamPanels.get(random.nextInt(teamPanels.size()));
            if (!panels.contains(randomSelectedPanel)) {
                panels.add(randomSelectedPanel);
            } else {
                i--;
            }
        }
        log.log(Level.INFO, "Panels have been assigned to player");
        return panels;
    }

    /**
     * Author Kamil Wasylkiewicz
     * Check if the player currently has the exact same panel as parameter
     *
     * @param panel The panel to be set
     */
    public boolean hasPanel(Panel panel) {
        if (panels == null) panels = new LinkedList<>();
        return panels.contains(panel);
    }

    /**
     * This method wil generate an instruction for this player based on a random panel and random value of the panel
     * author Kamil Wasylkiewicz     *
     *
     * @return a new made instruction
     */
    public synchronized Instruction generateInstruction() {
        Random random = new Random();
        // Get all the team panels
        List<Panel> teamPanels = (List<Panel>) team.getPanels();

        // Get a random panel
        Panel instructionPanel = teamPanels.get(random.nextInt(panels.size()));
        // create a random between the min en max value of a panel, however random has no constructor with min and max value
        // the solution is as follows
        // (panel.getmaxvalue -  panel.getminvalue) + panel.getminvalue
        // suppose example panel has minimal 2 and maximal 8 value
        // random.nextint(8-2)+2
        // this will always produce a random number from minimal 2 and maximal 8
        int intendedValue;
        if (instructionPanel.getPanelType() == PanelTypeEnum.Button) {
            intendedValue = 1;
        } else {
            intendedValue = random.nextInt(instructionPanel.getMaximumValue() - instructionPanel.getMinimumValue()) + instructionPanel.getMinimumValue();
        }
        // new instruction is made
        activeInstruction = new Instruction(instructionPanel, intendedValue, this);
        if (team.getActiveInstructions().contains(activeInstruction)) {
            generateInstruction();
        }
        log.log(Level.INFO, "Instruction " + activeInstruction.getPanel().getText() + " set to " + activeInstruction.getIntendedValue());

        return activeInstruction;
    }

}