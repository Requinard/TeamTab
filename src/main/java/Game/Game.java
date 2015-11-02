package Game;

import gui.StageController;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    private final int STARTTIMEROUND = 9;
    private final int STARTLEVENS = 3;
    Team team1;
    Team team2;
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;
    private ArrayList<String> playerScores;
    private int timeRound = 9;
    private int bonusCorrectInstructions;
    private int substractCorrectInstructions;
    private Player currentPlayer;
    private Instruction instruction = null;
    private StageController stageController;
    private boolean gameOver;

    /**
     * Initialize a game
     *
     * @author David
     */
    public Game(StageController stageController) {
        teams = new ArrayList<>();
        players = new ArrayList<>();
        instructions = new ArrayList<>();
        panels = new ArrayList<>();

        bonusCorrectInstructions = 3;
        substractCorrectInstructions = 5;
        this.stageController = stageController;
        gameOver = false;

        team1 = new Team(STARTTIMEROUND, STARTLEVENS, 0, "Team bots");
        team2 = new Team(STARTTIMEROUND, STARTLEVENS, 0, "Team2");

        teams.add(team1);
        teams.add(team2);
        // Load panels from CSV
        loadPanels();

        // EERSTE INTERATIE
        setUp();
    }

    //
    // THE FUNCTIONS DECLARED HERE ARE ONLY FOR DEMO PURPOSE, THEY WILL BE REMOVED AFTER REAL DATA WILL BE AVAILABLE
    // Author KAMIL
    //

    // de instantie van de betreffende game opvragen
    public Game getGame() {
        return this;
    }

    public boolean loadPanels() {
        //URL location = this.getClass().getClassLoader().getResource("panels.csv");

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/panels.csv")) {
            String full = IOUtils.toString(fileInputStream);

            // go over each line
            for (String s : full.split("\n")) {
                // If we start with a hashtag the line is commented and we skip it
                if (s.startsWith("#") || s.equals("\r"))
                    continue;

                // get the individual lines
                String[] split = s.split(",");

                // Parse the strings
                int id = Integer.parseInt(split[0].trim());
                int type = Integer.parseInt(split[1].trim());
                String text = split[2];
                int min = Integer.parseInt(split[3].trim());
                int max = Integer.parseInt(split[4].trim());

                // Create the panels
                Panel panel = new Panel(id, type, text, min, max);

                panels.add(panel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return panels.size() > 0;
    }

    // het opzetten van een demo speler zodat in de JoinView er een tegenstander aanwezig is
    private void setUp() {
        Panel panel = new Panel(1, 1, "Test", 0, 0);
        instruction = new Instruction(panel, "Test", 0);
        Player a = new Player("localhost", "BOT 1", 0, new ArrayList<Panel>(), instruction, this, team2);

        team1.addPlayerToTeam(a);
        System.out.println("Game - Demo players are made and added to teams (setUp)");

        //newRound();
    }

    // alle teams opvragen, in het begin zijn dit nog maar enkel 2 teams
    public List<Team> allTeams() {
        //System.out.println("Game - sire of ArrayList teams: " + teams.size());
        return Collections.unmodifiableList(teams);
    }

    // een speler opvragen op basis van zijn naam
    public Player getPlayerByName(String playerName) {
        for (Team t : teams) {
            for (Player p : t.getPlayers()) {
                if (p.getName().equals(playerName)) {
                    return p;
                }
            }
        }
        return null;
    }

    // een nieuw team aanmaken
    public boolean createTeam(String teamName) {
        team2.setName(teamName);

        for (Team t : teams) {
            if (t.getName().equals(teamName)) {
                System.out.println("Game - Team is created (createTeam)");
                return true;
            }
        }
        return false;
    }

    // een nieuwe speler aanmaken en deze aan de team toevoegen. Vervolgens de player instantie retourneren
    public Player createAndGetThisPlayer(String playerName, String teamName) {
        Team getTeam = null;
        for (Team t : teams) {
            if (t.getName().equals(teamName)) {
                getTeam = t;
            }
        }
        Player newPlayer = new Player("localhost", playerName, 0, panels, instruction, this, getTeam);
        currentPlayer = newPlayer;
        System.out.println("Game - Player is created (createAndGetThisPlayer)");
        return newPlayer;
    }

    // een intantie van Team opvragen op basis van teamnaam
    public Team getTeamOfPlayer() {
        for (Team allTeams : this.teams) {
            System.out.println(allTeams.getName());
            for(Player curPlayer : allTeams.getPlayers()){
                System.out.println(curPlayer.getName());
                if (curPlayer.getName().equals(currentPlayer.getName())) {
                    System.out.println("Game - team of player returned (getTeamOfPlayer)");
                    return allTeams;
                }
            }
        }
        return null;
    }

    //
    // END END END END
    //

    /**
     * @return the player for which the game starts
     * @Author Qun
     * OVERLOAD DEZE METHODE MET ipadress & username
     * OVERLOAD DEZE METHODE startgame()
     * TODO: OVERLOAD DEZE METHODE MET IPADRESS & USERNAME
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     */
    public Player startGame() {
        // setup voor demo spelers
        //
        //currentPlayer = new Player(player.getIpAdress(),player.getName(),player.getScore(),player.getPanels(),player.getInstruction(),player.getGame(),player.getTeam());
        // Check if both teams are the same size
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Panels are given to the teams that compete
            newRound();
            return currentPlayer;
        } else {
            throw new IllegalArgumentException("wrong sizes");
        }
    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     * Every Team receives new panels
     *
     * @return a new Round
     * @Author this method always returns false. Changed it to a void method
     */
    public void newRound() {
        // returns default values
        reset();
        loadPanels();
        for (Team team : teams) {
            team.givePanelsToPlayersFromTeam(panels);
        }
    }

    /**
     * In game.subtractlives the team that loses is deleted
     * from the list of teams.
     * Method checks if the size of team is <= 1 if this
     * is true the team wins the game.
     *
     * @param team The team that won the game
     * @return a list of players from the winning team + there score
     */
    public ArrayList<String> endGame(Team team) {
        // Sorts the players by score
        playerScores = new ArrayList<>();
        //playerScores.add("Succes!");

        for(Team allTeams : this.teams){
            if(allTeams.equals(team)){
                //playerScores.add("You loose!");
                playerScores.add(allTeams.getName());
                for(Player playersInLoosingTeam : allTeams.getPlayers()){
                    playerScores.add(playersInLoosingTeam.getName() + " score: " + playersInLoosingTeam.getScore());
                }
            }
            else{
                //playerScores.add("You Win!");
                for(Player playersWinningTeam : allTeams.getPlayers()){
                    playerScores.add(playersWinningTeam.getName() + " score: " + playersWinningTeam.getScore());
                }
            }
        }
        return playerScores;
    }

    /**
     * When joining a lobby the joined player is set into the team with the least amount of players
     *
     * @param player De player that is joining the lobby
     * @return true or false depending on if adding player to team is succesful
     * @Author Kaj
     */
    public boolean addPlayerToTeam(Player player) {
        // Adds players automaticly to a team when they join a lobby
        Team team = null;
        teams.size();
        for (int i = 0; i < teams.size() - 1; i++) {
            team = teams.get(i);
            if (teams.get(i).getPlayers().size() > teams.get(i + 1).getPlayers().size()) {
                team = teams.get(i + 1);
            }
        }
        assert team != null;
        return team.addPlayerToTeam(player);
    }

    /**
     * Changing the player to the other team
     *
     * @param player The player that wants to join the other team
     * @Author Kaj
     * @Return true when changing team is succesful
     */
    public boolean changeTeam(Player player) {
        Team currentTeam = player.getTeam();
        if (currentTeam != null) {
            int idTeam = teams.indexOf(currentTeam);
            currentTeam.removePlayer(player);
            if (idTeam + 1 < teams.size()) {
                return teams.get(idTeam + 1).addPlayerToTeam(player);
            } else {
                return teams.get(0).addPlayerToTeam(player);

            }
        } else {
            return false;
        }
    }

    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     *
     * @param team The team that gets checked
     * @author Frank Hartman
     */
    private boolean addTime(Team team) {
        if (team.getCorrectInstruction() == bonusCorrectInstructions) {
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
     *
     * @param team The team that gets a check if they should lose a life
     * @return true if the live of the team is subtracted
     * @Author Qun
     * @author Frank Hartman
     */
    public boolean subtractLives(Team team) {
        if (team.substractLives()) {
            if (team.getLives() <= 0) {
                //teams.remove(team);
                gameOver = true;
                endGame(team);
                return false;
            } else
                newRound();
        }
        return true;
    }

    /**
     * If the team has a certain winstreak the other them should get less time for there upcomming instructions
     *
     * @param currentTeam The team that gets checked
     * @return true when time has been substracted succesfully
     */
    public boolean subtractTime(Team currentTeam) {

        for (Team t : teams) {
            // Check if the team has got enough correct awnsers
            if (!t.equals(currentTeam) && currentTeam.getCorrectInstruction() >= substractCorrectInstructions) {
                // Other team gets a time penalty
                t.setTime(t.getTime() - 1);
                // Check if it is game over for the other team
                subtractLives(t);
                // Reset correct constructions to 0
                //currentTeam.setCorrectInstruction(0);
                break;
            }
        }
        return true;
    }

    /**
     * Adding a player to a team
     *
     * @param p
     * @param t
     */
    public void addPlayerToTeam(Player p, Team t) {
        if (!t.isPlayerInTeam(p)) {
            System.out.println("Game - Player is added to the team (addPlayerToTeam)");
            t.addPlayerToTeam(p);
        }
    }


    /**
     * Check if the executed instrucctions was correct and give the player a new one
     *
     * @param changedPanel The panel that has been pressed
     * @param player       The player that gets checked
     * @Author Qun
     * @Return true when the instruction is correct, false when instruction is wrong
     */
    public void checkInstruction(Panel changedPanel, Player player, int sliderValue) { //player kan gevonden worden door op panel te zoeken
        Team t = player.getTeam();

        if (t.checkTeamInstruction(changedPanel, sliderValue)) {
            System.out.println("The instruction was correct!");
            // Check the bonus for streaks
            addTime(t);
            subtractTime(t);
            System.out.println("Team time: " + t.getTime());
            System.out.println("Team lives: " + t.getLives());
        } else {
            System.out.println("The instruction was not correct");
            // Check if the team should lose a life
            t.decreaseTime();
            System.out.println("Team time: " + t.getTime());
            System.out.println("Team lives: " + t.getLives());
            subtractLives(t);
        }
    }

    /**
     * @author Kamil Wasylkiewicz
     * this methods returns the value of the boolean
     * @return the boolean gameover
     */
    public boolean gameOver() {
        return this.gameOver;
    }


    /**
     * When there is no time to execute a instruction this method should be called
     *
     * @author Frank Hartman
     */
    public void instructionIsToLate(Player player) {
        Panel panel = new Panel(1000, 1, "WRONG PANNEL", 0, 0);
        checkInstruction(panel, player, 1);
        System.out.println("INSTRUCTION TO LATE! current instruction time: " + player.getTeam().getTime());
    }

    /**
     * @return true when resetting team is succesful else false
     * @Author Qun ik snap niet wanneer het fout kan gaan ?
     * Nu aangepast zodat het een void is.
     * Reset values from both teams
     */
    public boolean reset() {
        boolean teamHasBeenReset = false;
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
            t.resetTeam();
            teamHasBeenReset = true;
        }
        return teamHasBeenReset;
    }

    /**
     * Returns the panels from the player
     *
     * @param player
     * @return playerPanels
     */
    private ArrayList<Panel> returnPanelsPlayer(Player player) {
        return player.getPanels();
    }
}
