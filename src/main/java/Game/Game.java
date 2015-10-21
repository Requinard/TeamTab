package Game;

import java.util.*;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;
    private ArrayList<String> playerScores;

    Team team1;
    Team team2;
    private int timeRound;
    private int bonusCorrectInstructions;
    private int substractCorrectInstructions;

    public Game(){
        final int STARTLEVENS = 3;
        final int STARTTIMEROUND = 9;

        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        bonusCorrectInstructions = 3;
        substractCorrectInstructions = 5;

        team1 = new Team(STARTTIMEROUND, STARTLEVENS);
        team2 = new Team(STARTTIMEROUND, STARTLEVENS);

        teams.add(team1);
        teams.add(team2);
    }

    /**
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @return true if they are the same
     */
    public boolean startGame(){
        // Check if both teams are the same size
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Panels are given to the teams that compete
            return true;
        }else {
            throw new IllegalArgumentException ("wrong sizes");
        }

    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     *
     * @Author Qun
     * @return a new Round
     */
    public boolean newRound(){
        // returns default values
        reset();
        return false;
    }

    /**
     *
     * @param team The team that won the game
     * @return a list of players from the winning team + there score
     */
    public ArrayList<String> endGame(Team team){
        // Sorts the players by score
        playerScores = new ArrayList<String>();
        List<Player> sortedWinningTeam = team.sortedPlayerByScore();

        if (teams.size() <= 0)
            playerScores.add("You won!");
        else
            playerScores.add("You lost!");

        if (sortedWinningTeam != null) {
            for (Player p : sortedWinningTeam) {
                playerScores.add(p.getName() + ": " + p.getScore());
            }
            return playerScores;
        }

        else
            return null;
    }

    /**
     * When joining a lobby the joined player is set into the team with the least amount of players
     * @Author Qun
     * @param player De player that is joining the lobby
     * @return true or false depending on if adding player to team is succesful
     */
    public boolean addPlayerToTeam(Player player){
        // Adds players automaticly to a team when they join a lobby
        if(teams.get(0).getPlayers().size() <= teams.get(1).getPlayers().size())
        {
            player.setTeam(team1);
            return team1.addPlayerToTeam(player);
        }else {
            player.setTeam(team2);
            return team2.addPlayerToTeam(player);
        }
    }

    /**
     * Changing the player to the other team
     *
     * @Author Qun
     * @param player The player that wants to join the other team
     * @Return true when changing team is succesful
     */
    public boolean changeTeam(Player player){

        if (team1.getPlayers().contains(player)) {
            // Player gets added to team 2
            if (team1.removePlayer(player)) {
                player.setTeam(team2);
                return team2.addPlayerToTeam(player);
            }
        }

        else {
            // Player gets added to team 1
            if (team2.removePlayer(player)) {
                player.setTeam(team1);
                return team1.addPlayerToTeam(player);
            }
        }
        return false;
    }



    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     * @param team The team that gets checked
     */
    private boolean addTime(Team team){
        if (team.getCorrectInstruction() == bonusCorrectInstructions){

            team.setTime(team.getTime() + 1);
            return true;
        }
        return false;
    }

    /**
     * When the team has less than 3 seconds it should lose a life
     * @param losingTeam The team that gets a check if they should lose a life
     * @return true if the given time had less than 3 seconds
     */
    public boolean subtractLives(Team losingTeam){
        if (losingTeam.getTime() <= 3) {
            losingTeam.substractLives();

            if (losingTeam.getLives() <= 0) {
                // Remove the team from the teams in the game
                teams.remove(losingTeam);
                // End the game for the given team
                endGame(losingTeam);
            }
            else{
                newRound();
            }
            return true;
        }

        else
            return false;
    }

    /**
     * If the team has a certain winstreak the other them should get less time for there upcomming instructions
     * @param currentTeam The team that gets checked
     * @return true when time has been substracted succesfully
     */
    public boolean subtractTime(Team currentTeam){

        for (Team t : teams) {
            // Check if the team has got enough correct awnsers
            if (!t.equals(currentTeam) && currentTeam.getCorrectInstruction() >= substractCorrectInstructions){
                Team otherTeam = t;
                // Other team gets a time penalty
                otherTeam.setTime(otherTeam.getTime() - 1);
                // Check if it is game over for the other team
                subtractLives(otherTeam);
                // Reset correct constructions to 0
                currentTeam.setCorrectInstruction(0);
                break;
            }
        }
        return true;
    }



    /**
     * Check if the executed instrucctions was correct and give the player a new one
     * @Author Qun
     * @param donePanel The panel that has been pressed
     * @param player The player that gets checked
     * @Return true when the instruction is correct, false when instruction is wrong
     */
    public boolean checkInstruction(Panel donePanel, Player player){ //player kan gevonden worden door op panel te zoeken
        Team t = player.getTeam();
        int currentCorrect = t.getCorrectInstruction();
        boolean instructionCorrect = false;

        for (Player p : t.getPlayers()) {

            if (p.checkCorrectPanel(donePanel)) {

                t.setCorrectInstruction(currentCorrect + 1);
                addTime(t);
                instructionCorrect = true;
            } else {
                t.setCorrectInstruction(0);
            }
        }
        return instructionCorrect;
    }

    /**
     * Reset values from both teams
     * @return true when resetting team is succesful else false
     */
    private boolean reset(){
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
            if (!t.resetTeam()){
                return false;
            }
        }
        return true;
    }

    /**
     * Gives a player a new instruction
     * @param player The player that needs a new instruction
     */
    private Instruction givePlayerInstructions(Player player) {
        Team playerTeam = player.getTeam();
        int maxSize = playerTeam.getPlayerPanels().size();
        Random random = new Random();
        // Panels that are in use
        ArrayList<Panel> usedPanelNumbers = new ArrayList<Panel>();
        // Panels that are not in use and that cannot be chosen
        ArrayList<Panel> unusedPanelNumbers = playerTeam.getPlayerPanels();

        // Gets all the panels wich are used by the team of the player
        for (Player p : playerTeam.getPlayers()) {
            usedPanelNumbers.add(p.getInstructions().getPanel());
        }

        // Removes all the panels that are in use so only the available panels remain
        for (Panel p : usedPanelNumbers) {
            unusedPanelNumbers.remove(p);
        }

        // gets a random panel from the list of panels
        Panel panel = unusedPanelNumbers.get(random.nextInt(maxSize));

        // Add the random instruction to the player
        Instruction instuction = panel.getInstruction();
        player.setInstructions(instuction);

        // Returns the instruction
        return instuction;
    }
}
