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
     * @param username
     * @param ip
     */
    public Player(String username, String ip) {
        // TODO - implement Player.Player
        throw new UnsupportedOperationException();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return this.ip;
    }

    public List<Panel> getPanels() {
        return this.panels;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Instruction getActiveInstruction() {
        return this.activeInstruction;
    }

    /**
     * This method generates random panels for a player. A player gets 12 random panels
     *
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
	 * 
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