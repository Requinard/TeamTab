package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    Team team1;
    Team team2;
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;
    private ArrayList<String> playerScores;
    private int timeRound =9;
    private int bonusCorrectInstructions;
    private int substractCorrectInstructions;

    // THE VARIABLES DECLARED HERE ARE ONLY FOR DEMO PURPOSE, THEY WILL BE REMOVED AFTER REAL DATA WILL BE AVAILABLE

    Panel panel = new Panel(1,1,"Demo Panel", 1,1);
    Instruction instruction = new Instruction(panel,"DemoCommando", 1);

    // END DEMO VARIABLES


    public Game(){
        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        // waarom worden hier 2 teams aangemaakt en de regel eronder dezelfde teams met waarden. onlogisch.
        team1 = new Team(0, 0, 0, "Team1");
        team2 = new Team(0, 0, 0, "Team2");

        team1 = new Team(timeRound, 3, 0, "Team1");
        team2 = new Team(timeRound, 3, 0, "Team2");

        teams.add(team1);
        teams.add(team2);

    }

    //
    // THE FUNCTIONS DECLARED HERE ARE ONLY FOR DEMO PURPOSE, THEY WILL BE REMOVED AFTER REAL DATA WILL BE AVAILABLE
    // Author KAMIL
    //

    // de instantie van de betreffende game opvragen
    public Game getGame (){
        return this;
    }


    // het opzetten van een demo speler zodat in de JoinView er een tegenstander aanwezig is
    private void setUp(){
        Player a = new Player("localhost","Donnie Brasco",0,panels,instruction,this, team2);
        team2.addPlayerToTeam(a);
        System.out.println("Game - Demo players are made and added to teams (setUp)");
    }

    // alle teams opvragen, in het begin zijn dit nog maar enkel 2 teams
    public List<Team> allTeams(){
        System.out.println("Game - sire of ArrayList teams: " + teams.size());
         return Collections.unmodifiableList(teams);
    }

    // een speler opvragen op basis van zijn naam
    public Player getPlayerByName(String playerName){
        for(Team t : teams){
            for(Player p: t.getPlayers()){
                if(p.getName().equals(playerName)){
                    return p;
                }
            }
        }
        return null;
    }

    // een nieuw team aanmaken
    public boolean createTeam(String teamName){
        Team newTeam = new Team(9,3,0,teamName);
        teams.add(newTeam);

        for(Team t : teams){
            if(t.getName().equals(teamName)){
                System.out.println("Game - Team is created (createTeam)");
                return true;
            }
        }
        return false;
    }

    // een nieuwe speler aanmaken en deze aan de team toevoegen. Vervolgens de player instantie retourneren
    public Player createAndGetThisPlayer(String playerName, String teamName){
        Team getTeam = null;
        for(Team t : teams){
            if(t.getName().equals(teamName)){
                getTeam = t;
            }
        }
        Player newPlayer = new Player("localhost", playerName, 0, panels, instruction,this, getTeam);
        System.out.println("Game - Player is created (createAndGetThisPlayer)");
        return  newPlayer;
    }

    // een intantie van Team opvragen op basis van teamnaam
    public Team getTeamByName(String teamName){
        for(Team t : teams){
            if(t.getName().equals(teamName)){
                System.out.println("Game - Team is found and returned (getTeamByName)");
                return t;
            }
        }
        return null;
    }

    //
    // END END END END
    //

    /**
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @return true if they are the same
     */
    public boolean startGame(){
        // Check if both teams are the same size
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Geeft de teams die meedoen panels

            // demo method KAMIL

            setUp();

            // END

            System.out.println("Game - Game has started (startGame())");
            return true;
        }else {
            throw new IllegalArgumentException ("wrong sizes");
        }
    }

    /**
     * Een nieuwe ronde dient gestart te worden wanneer een van de twee teams een leven verliest
     * Alle waardes in de game worden dan vervolgens gereset
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
        ArrayList<Player> sortedWinningTeam = winningTeam.sortedPlayerByScore();
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
    public  void changeTeam(Player player){

        if (team1.getPlayers().contains(player)) {
            // Speler wordt toegevoegd aan team 2
            if (team1.removePlayer(player)) {
                player.setTeam(team2);
                team2.addPlayerToTeam(player);
            }

            else
                throw new IllegalArgumentException("Player is not able to join other team");
        }

        else {
            // Speler wordt toegevoegd aan team 1
            if (team2.removePlayer(player)) {
                player.setTeam(team1);
                team1.addPlayerToTeam(player);
            }
            else
                throw new IllegalArgumentException("Player is not able to join other team");

        }

    }



    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     * @param tm The team that gets checked
     */
    private void addTime(Team tm){

        if (tm.getCorrectInstruction() == bonusCorrectInstructions){

            tm.setTime(tm.getTime() + 1);
        }
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
//        if (!t.addPlayerToTeam(p)){
//            throw new IllegalArgumentException("Player can not be added to the team");
//        }
//        //p.setTeam(t); //aanpassing team set
//        //ArrayList<Player> excitingPlayers = t.getPlayers(); //toch fout
//        players.add(p);
//        //t.setPlayers(excitingPlayers);

        if(!t.isPlayerInTeam(p)){
            System.out.println("Game - Player is added to the team (addPlayerToTeam)");
            t.addPlayerToTeam(p);
        }
    }


    /**
     * Check if the executed instrucctions was correct and give the player a new one
     * @param donePanel The panel that has been pressed
     * @param player The player that gets checked
     */
    public void addCorrectInstruction(Panel donePanel, Player player){ //player kan gevonden worden door op panel te zoeken
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
    public void reset(){
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
            t.resetTeam();
        }
        System.out.println("Game - Teams are reset (reset())");
    }

    /**
     * FRANK: SAMEN MET KAMIL NAAR KIJKEN
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
    //    player.setInstructions(panel.getInstruction());

    }
}
