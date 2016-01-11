package Game;

import java.util.LinkedList;
import java.util.List;

/**
 * Edited on david on 11-1-16.
 */
public class LocalGame {
    public Player player;
    public Team team;
    public List<Panel> panels = new LinkedList<>();
    private String hostIP = "";
    private String localIP = "";
    private Instruction instruction;
    private List<String> scoreBoard = new LinkedList<>();

    public List<String> getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(List<String> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Panel> getPanels() {
        return panels;
    }

    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }
}
