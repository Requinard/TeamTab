package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;
    private ArrayList<String> playerScores;

    Team team1 = new Team();
    Team team2 = new Team();
    private int timeRound;
    private int bonusCorrectInstructions;
    private int substractCorrectInstructions;

    public Game(){
        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        teams.add(team1);
        teams.add(team2);

        // Standaard tijd voor een ronde
        timeRound = 9;
        bonusCorrectInstructions = 3;
        substractCorrectInstructions = 5;

    }

    public void startGame(){
        //? maak instucties voor players ?
        // Kijkt of beide teams evenveel spelers hebben
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            getAllPlayerPanels(team1);
            getAllPlayerPanels(team2);
        }

    }

    public void newRound(){
        // Terug zetten van standaard waardes
        reset();

        //nieuw panels voor players
    }

    // RETURN DE LIJST MET SCORES?
    public void endGame(){

        // Sorteren van de spelers op score
        try {
            Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return Integer.compare(o1.getScore(), o2.getScore());
                }
            });
        }

        catch (Exception ex) {
            // Geef een messagebox met een error
        }

        for (Player p : players){
            // Scores opslaan in een lijst met namen + de score
            playerScores.add(p.getName() + ": " + p.getScore());
        }
    }

    public void addPlayerToTeam(Player p){
        // Automatisch toevoegen van spelers aan een team wanneer ze de lobby joinen
        if(teams.get(0).getPlayers().size() <= teams.get(1).getPlayers().size())
        {
            addPlayer(p, team1);
        }else {
            addPlayer(p, team2);
        }
    }

    public  void changeTeam(Player p){
        // Veranderen van de speler van het een naar het andere team
        if (team1.getPlayers().contains(p)) {
            team1.getPlayers().remove(p);
            team2.getPlayers().add(p);
        }

        else {
            team2.getPlayers().remove(p);
            team1.getPlayers().add(p);
        }


    }

    // TEAM METHODS

    private void addTime(Team t){
        // ook nog met timer tijd aftrek
        if (t.getCorrectInstruction() == bonusCorrectInstructions){
            t.setTime(t.getTime() + 1);
            resetCorrectInstruction(t);
        }
    }

    public void subtractLives(Team losingTeam){
        if (losingTeam.getTime() <= 3){
            losingTeam.setLives(losingTeam.getLives() - 1);
            if (losingTeam.getLives() <= 0)
                endGame();
            else
                newRound();
        }
        //new round start
    }

    public void subtractTime(Team currentTeam){
        //voorbeeld van time correctie op goede otherteam
        for (Team t : teams) {
            if (!t.equals(currentTeam) && currentTeam.getCorrectInstruction() == substractCorrectInstructions){
                Team otherTeam = t;
                otherTeam.setTime(otherTeam.getTime() - 1);
                subtractLives(otherTeam);
                break;
            }
        }

    }
    public void addPlayer(Player p, Team t){
        t.getPlayers().add(p);
    }

    // NOG OP VERANDERING WACHTEN VAN QUNFONG
    public void addCorrectInstruction(Panel donePanel, Player player){
        Team t = player.getTeam();
        int currentCorrect = t.getCorrectInstruction();


        for (Player p : t.getPlayers()){

            if (p.getInstructions().getPanel() == donePanel) {
                t.setCorrectInstruction(currentCorrect + 1);
                givePlayerInstructions(p);
                addTime(t);
            }
            //player instruction controleren op doorgegeven Paneel
        }
        if (!(currentCorrect < t.getCorrectInstruction())){
            resetCorrectInstruction(t);
        }
    }

    private void resetCorrectInstruction(Team t) {
        if (t.getCorrectInstruction() >= substractCorrectInstructions)
            t.setCorrectInstruction(0);
    }

    private void reset(){
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
            // Resetten van de tijd naar 9 seconden
            t.setTime(timeRound);
            // Het aantal correcte instructies terugzetten op 0
            t.setCorrectInstruction(0);
        }
    }
    public void getAllPlayerPanels(Team t){
        ArrayList<Panel> tempPanels = new ArrayList<>();
        for (Player p : t.getPlayers()){
            tempPanels.add(p.getPanel());
        }
        t.setPlayerPanels(tempPanels);

    }

    private void givePlayerInstructions(Player player) {
        Team playerTeam = player.getTeam();
        int maxSize = playerTeam.getPlayerPanels().size();
        Random random = new Random();
        ArrayList<Panel> usedPanelNumbers = new ArrayList<>();
        ArrayList<Panel> unusedPanelNumbers = playerTeam.getPlayerPanels();


        for (Player p : playerTeam.getPlayers()) {
            usedPanelNumbers.add(p.getInstructions().getPanel());
        }

        for (Panel p : usedPanelNumbers) {
            unusedPanelNumbers.remove(p);
        }

        Panel panel = unusedPanelNumbers.get(random.nextInt(maxSize));

        player.setInstructions(panel.getInstruction());

    }
}
