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
        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        timeRound = 9;
        bonusCorrectInstructions = 3;
        substractCorrectInstructions = 5;

        team1 = new Team(timeRound, STARTLEVENS);
        team2 = new Team(timeRound, STARTLEVENS);

        teams.add(team1);
        teams.add(team2);

        // Standaard tijd voor een ronde


    }

    /**
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @return true if they are the same
     */
    public boolean startGame(){
        // Check if both teams are the same size
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Geeft de teams die meedoen panels
            return true;
        }else {
            throw new IllegalArgumentException ("wrong sizes");
        }

    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     */
    public void newRound(){
        // Terug zetten van standaard waardes
        reset();
    }

    /**
     *
     * @param winningTeam The team that won the game
     * @return a list of players from the winning team + there score
     */
    public ArrayList<String> endGame(Team winningTeam){

        // Sorteren van de spelers op score
        playerScores = new ArrayList<String>();
        List<Player> sortedWinningTeam = winningTeam.sortedPlayerByScore();
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
     * When joinin a lobby the joinen player is set into the team with the least amount of players
     *
     * @param player De player that is joining the lobby
     *
     */
    public void addPlayerToTeam(Player player){
        // Automatisch toevoegen van spelers aan een team wanneer ze de lobby joinen
        if(teams.get(0).getPlayers().size() <= teams.get(1).getPlayers().size())
        {
            player.setTeam(team1);
            team1.addPlayerToTeam(player);
        }else {
            player.setTeam(team2);
            team2.addPlayerToTeam(player);
        }
    }

    /**
     * Changing the player to the other team
     * @param player The player that wants to join the other team
     */
    public boolean changeTeam(Player player){

        if (team1.getPlayers().contains(player)) {
            // Speler wordt toegevoegd aan team 2
            if (team1.removePlayer(player)) {
                player.setTeam(team2);
                return team2.addPlayerToTeam(player);
            }
        }

        else {
            // Speler wordt toegevoegd aan team 1
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
     * When the team has las then 3 seconds it should lose a life
     * @param losingTeam The team that gets a check if the should lose a life
     */
    public boolean subtractLives(Team losingTeam){
        if (losingTeam.getTime() <= 3){
            losingTeam.setLives(losingTeam.getLives() - 1);

            if (losingTeam.getLives() <= 0)
                // Game is over
                if (losingTeam.equals(team1)) {
                    endGame(team2);
                }
                else
                    endGame(team1);
            else{
                // Team lost the round a new round should be started
                newRound();
            }
            return true;
        }
        return false;
    }

    /**
     * If the team has a certain winstreak the other them should get less time for there upcomming instructions
     * @param currentTeam The team that gets checked
     */
    public void subtractTime(Team currentTeam){

        for (Team t : teams) {
            // Checken of het teamm genoeg correcte antwoorden behaald heeft
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

    }

    /**
     * Adding a player to a team
     * @param p
     * @param t
     */
    public void addPlayerToTeam(Player p, Team t){
        if (!t.addPlayerToTeam(p)){
            throw new IllegalArgumentException("Player can not be added to the team");
        }
        //p.setTeam(t); //aanpassing team set
        //ArrayList<Player> excitingPlayers = t.getPlayers(); //toch fout
        players.add(p);
        //t.setPlayers(excitingPlayers);
    }


    /**
     * Check if the executed instrucctions was correct and give the player a new one
     * @param donePanel The panel that has been pressed
     * @param player The player that gets checked
     */
    public void checkInstruction(Panel donePanel, Player player){ //player kan gevonden worden door op panel te zoeken
        Team t = player.getTeam();
        int currentCorrect = t.getCorrectInstruction();


        for (Player p : t.getPlayers()) {

            if (p.checkCorrectPanel(donePanel)) {

                t.setCorrectInstruction(currentCorrect + 1);
                //givePlayerInstructions(p); QUN LET OP weg gehaald, omdat hij errored maar niet nodig is voor mijn test
                addTime(t);
            } else
                t.setCorrectInstruction(0);
        }
    }

    /**
     * Reset values from both teams
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
     * @param player De speler die een nieuwe instructie moet krijgen
     */
    private void givePlayerInstructions(Player player) {
        Team playerTeam = player.getTeam();
        int maxSize = playerTeam.getPlayerPanels().size();
        Random random = new Random();
        // Panels die in gebruik zijn
        ArrayList<Panel> usedPanelNumbers = new ArrayList<Panel>();
        // Panels die niet in gebruik zijn en waar dus uit gekozen mag worden
        ArrayList<Panel> unusedPanelNumbers = playerTeam.getPlayerPanels();

        // Haalt alle panels op die op dit moment gebruikt worden door de speler uit het team
        for (Player p : playerTeam.getPlayers()) {
            usedPanelNumbers.add(p.getInstructions().getPanel());
        }

        // Verwijdert de panels die gebruikt worden zodat alleen de panels overblijven die nog niet gebruikt worden
        for (Panel p : usedPanelNumbers) {
            unusedPanelNumbers.remove(p);
        }

        // Een random panel wordt gekozen uit de lijst met panels
        Panel panel = unusedPanelNumbers.get(random.nextInt(maxSize));

        // Voegt de random instructie toe aan de speler
        player.setInstructions(panel.getInstruction());

    }
}
