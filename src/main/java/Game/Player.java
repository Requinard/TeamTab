package Game;

import java.util.Collection;
import java.util.List;

public class Player {

    private String username;
    private String ip = "127.0.0.1";
    private Collection<Panel> panels;
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

    public Collection<Panel> getPanels() {
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

    public java.util.List<Panel> generatePanels() {
        // TODO - implement Player.generatePanels
        throw new UnsupportedOperationException();
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