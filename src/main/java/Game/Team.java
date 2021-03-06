package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Team {

    private static final Logger log = Logger.getLogger(Team.class.getName());
    private final int STARTLIVES = 3;
    private final int STARTTIME = 9;
    private transient List<Player> players = new LinkedList<>();
    private transient List<Panel> panels = new LinkedList<>();
    private String name;
    private transient List<Instruction> activeInstructions = new LinkedList<>();
    private int lives = STARTLIVES;
    private int time = STARTTIME;
    private int score;

    /**
     * @param name
     */
    public Team(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Second constructor for the adapters.teamadapter sendable method
     * Author Kamil Wasylkiewicz
     *
     * @param name       the name of the team
     * @param startLives the start lives of the team
     * @param starTime   the starting time of the team
     * @param score      the score of the team
     */
    public Team(String name, int startLives, int starTime, int score) {
        this.name = name;
        this.lives = startLives;
        this.time = starTime;
        this.score = score;
    }


    /**
     * Author Qun
     * returns the players in the team
     *
     * @return List containing all players of the team
     */
    public List<Player> getPlayers() {
        if (players == null) players = new ArrayList<>();
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Author Qun
     * returns all the panels the team has
     *
     * @return List of all panels a team has
     */
    public Collection<Panel> getPanels() {
        return this.panels;
    }

    /**
     * Author Qun
     * returns the name of the team
     *
     * @return The name of the team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Author Qun
     * returns all active instructions the team has
     *
     * @return All active instructions of a team
     */
    public List<Instruction> getActiveInstructions() {
        return this.activeInstructions;
    }

    /**
     * Author Qun
     * returns the lives the team currently has
     *
     * @return The amount of lives the team has
     */
    public synchronized int getLives() {
        return this.lives;
    }

    /**
     * Author Qun
     * returns the time the team has per round
     *
     * @return The time the team has
     */
    public synchronized int getTime() {
        return this.time;
    }

    /**
     * Author Qun
     * returns the current score of the team
     *
     * @return The score of the team
     */
    public int getScore() {
        return score;
    }


    /**
     * Checks if the list is empty or if the player is already in the team
     * if there is a team and the person is not in it, the player is added to the team
     * Author Qun, Kaj
     *
     * @param player
     */
    public Player addPlayer(Player player) {
        if (this.players == null)
            this.players = new ArrayList<>();
        if (players.size() == 0 || !players.contains(player)) {
            players.add(player);
            player.setTeam(this);
            log.log(Level.INFO, "Player has been added to the team");
            return player;
        }
        log.log(Level.INFO, "Player has not been added to the team");
        player = null;
        return player;
    }


    /**
     * Removes player if it is in the team
     * Author Qun, Kaj
     *
     * @param player
     */
    public Player removePlayer(Player player) {
        for (Player teamPlayers : players) {
            if (teamPlayers.getIp().equals(player.getIp())) {
                players.remove(player);
                log.log(Level.INFO, "Player " + teamPlayers.getUsername() + " is removed from the team");
            } else {
                player = null;
                log.log(Level.INFO, "Player is not removed from the team");
            }
            return player;
        }
        return null;
    }

    /**
     * This method generates panels for the team based on the amount of players
     * It can't contain the same panels.
     * Author Qun, Kaj
     *
     * @param gamePanels this is a list of panels gotten from the Game
     * @return panels       this is a list of panels that is given to a team
     */
    public List<Panel> generatePanels(List<Panel> gamePanels) {
        panels.clear();
        List<Panel> gamePanelsCopy = new ArrayList<Panel>();
        gamePanelsCopy.addAll(gamePanels);
        List<Panel> panelsGivenToPlayer;
        for (Player player : players) {
            panelsGivenToPlayer = player.generatePanels(gamePanelsCopy);
            this.panels.addAll(player.getPanels());
            generateInstructionForPlayer(player);
            gamePanelsCopy.removeAll(panelsGivenToPlayer);
        }
        return gamePanels;
    }

    /**
     * Give the player a new instruction and remove his old instruction from the active instruction
     *
     * @param player The player that will get a new instruction
     * @return true if the player has the new instruction
     */
    public synchronized Instruction generateInstructionForPlayer(Player player) {
        Instruction oldInstruction = player.getActiveInstruction();

        // Generate a new instruction for the player and add this instruction to the active instruction
        Instruction instruction = player.generateInstruction();
        player.setActiveInstruction(instruction);
        activeInstructions.add(instruction);

        if (oldInstruction != null) {
            //Remove the current instruction from the active instructions
            activeInstructions.remove(oldInstruction);
            log.log(Level.INFO, "Removed the old instruction from the active instructions");
        }

        return instruction;// == player.getActiveInstruction();
    }

    /**
     * Change the lives by the amount of amount
     * Author Kaj
     *
     * @param amount amount may be positive or negative
     */
    public synchronized void changeLives(int amount) {
        lives += amount;
    }

    /**
     * Change the time by the amount of amount
     * Author Kaj
     *
     * @param amount amount may be positive or negative
     */
    public synchronized int changeTime(int amount) {
        return time += amount;
    }


    /**
     * Check if the state of the team has been changed
     * Author Frank Hartman
     *
     * @return true of it changed
     */
    public boolean hasChanged() {
        return STARTLIVES != lives || STARTTIME != time || !activeInstructions.isEmpty() || score > 0;
    }

    /**
     * Author Qun
     * When the team doesn't have anymore lives, the team is not alive.
     *
     * @return True if the team is still alive, false if team has lost
     */
    public boolean isAlive() {
        if (getLives() > 0) {
            log.log(Level.INFO, "Team has {0} lives", lives);
            return true;
        }
        log.log(Level.INFO, "Team is not alive");
        return false;
    }

    /**
     * Reset the data of the team
     * Author Frank Hartman
     *
     * @param hard if true the amount of lives will be set to the beginning amount of time
     */
    public void reset(boolean hard) {
        // If it is a hard reset then reset the lives
        if (hard) {
            lives = STARTLIVES;
            score = 0;
            System.out.println(lives + " " + score + " " + "playersize " + players.size());
        }
        time = STARTTIME;
        log.log(Level.INFO, String.format("Team: %s has been reset, hard reset = %s", name, hard));
    }

    /**
     * Checks if the panel belongs to a active instruction
     * if so, score + 1, remove the instruction from the active list and return true
     * Author Kaj
     *
     * @param panel the pressed panel
     * @return true if the pressed panel was a active instruction
     */
    public synchronized Instruction validateInstruction(Panel panel) {
        Instruction correctInstruction = null;
        for (Instruction instruction : activeInstructions) {
            if (instruction.getPanel().getId() == panel.getId()) {
                if (panel.getPanelType() == PanelTypeEnum.HorizontalSlider || panel.getPanelType() == PanelTypeEnum.VerticalSlider) {
                    if (instruction.getPanel().getValue() != panel.getValue()) {
                        return correctInstruction;
                    }
                }
                score++;
                correctInstruction = instruction;
                //activeInstructions.remove(instruction);
                log.log(Level.INFO, "validating instruction ended, panel: {0} was correct", panel.getText());
                return correctInstruction;
            }
        }
        log.log(Level.INFO, "validating instruction ended, panel: {0} was incorrect", panel.getText());
        return correctInstruction;
    }

    @Override
    public String toString() {
        return this.name;
    }
}