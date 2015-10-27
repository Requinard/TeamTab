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
    private Player currentPlayer;

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
     * @Author Qun
     * OVERLOAD DEZE METHODE MET ipadress & username
     * OVERLOAD DEZE METHODE startgame()
     * TODO: OVERLOAD DEZE METHODE MET IPADRESS & USERNAME
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @return the player for which the game starts
     */
    public Player startGame(Player player){
        currentPlayer = new Player(player.getIpAdress(),player.getName(),player.getScore(),player.getPanels(),player.getInstructions(),player.getGame(),player.getTeam());
        // Check if both teams are the same size
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Panels are given to the teams that compete
            return currentPlayer;

        }else {
            throw new IllegalArgumentException ("wrong sizes");
        }

    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     * Every Team receives new panels
     * @Author this method always returns false. Changed it to a void method
     * @return a new Round
     */
    public void newRound(){
        // returns default values
        reset();
        for(Team team : teams)
        {
            team.setPlayerPanels(panels);
        }

        //return false;
    }

    /**
     * In game.subtractlives the team that loses is deleted
     * from the list of teams.
     * Method checks if the size of team is <= 1 if this
     * is true the team wins the game.
     * @param team The team that won the game
     * @return a list of players from the winning team + there score
     */
    public ArrayList<String> endGame(Team team){
        // Sorts the players by score
        playerScores = new ArrayList<String>();
        List<Player> sortedWinningTeam = team.sortedPlayerByScore();

        if (teams.size() <= 1)
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
     * @Author Kaj
     * @param player De player that is joining the lobby
     * @return true or false depending on if adding player to team is succesful
     */
    public boolean addPlayerToTeam(Player player){
        // Adds players automaticly to a team when they join a lobby
        Team team = null;
        teams.size();
        for(int i=0; i<teams.size()-1; i++){
            team = teams.get(i);
            if (teams.get(i).getPlayers().size() > teams.get(i+1).getPlayers().size()){
                team = teams.get(i+1);
            }
        }
        return team.addPlayerToTeam(player);
    }

    /**
     * Changing the player to the other team
     * @Author Qun changed the if statement so false can be returned
     * @Author Kaj
     * @param player The player that wants to join the other team
     * @Return true when changing team is succesful
     */
    public boolean changeTeam(Player player){
        Team currentTeam = player.getTeam();
        if(currentTeam != null)
        {
            int idTeam = teams.indexOf(currentTeam);
                currentTeam.removePlayer(player);
                if (idTeam+1 < teams.size()) {
                    return teams.get(idTeam+1).addPlayerToTeam(player);
                }else {
                    return teams.get(0).addPlayerToTeam(player);

        }
        }else {
            return false;
        }
    }

    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     * @author Frank Hartman
     * @param team The team that gets checked
     */
    private boolean addTime(Team team){
        if (team.getCorrectInstruction() == bonusCorrectInstructions){
            int maxTime = 9;
            int addTime = 1;
            team.addTeamTime(addTime, maxTime);
            return true;
        }
        return false;
    }

    /**
     * When the team has less than 3 seconds they lose a life
     * if  they have 0 lives that teams game is ended
     * @Author Qun
     * @param team The team that gets a check if they should lose a life
     * @return true if the live of the team is subtracted
     */
    public boolean subtractLives(Team team){
        if(team.substractLives() && team.getLives() <= 0) {
            teams.remove(team);
            endGame(team); // dit moet in de controller worden aangeroepen
            return true;
        }
        else if(team.substractLives()) {
            return true;
        }
        else{
            return false;
        }

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
     * @param changedPanel The panel that has been pressed
     * @param player The player that gets checked
     * @Return true when the instruction is correct, false when instruction is wrong
     */
    public boolean checkInstruction(Panel changedPanel, Player player){ //player kan gevonden worden door op panel te zoeken
        Team t = player.getTeam();

        if (t.checkTeamInstruction(changedPanel)) {
            if (addTime(t))
                return true;
            else
                return false;
        }

        else
            return false;

    }

    /**
     * @Author Qun ik snap niet wanneer het fout kan gaan ? Misschien bij starttime setten in resetTeam() ?
     * Nu aangepast zodat het een void is.
     * Reset values from both teams
     * @return true when resetting team is succesful else false
     */
    private boolean reset(){
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
             t.resetTeam();
            return true;
            }
        return false;
        }

    /**
     * Returns the panels from the player
     * @param player
     * @return playerPanels
     */
    private ArrayList<Panel> returnPanelsPlayer(Player player)
    {
        return player.getPanels();
    }
}
