package Game;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public Game(){
        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        teams.add(team1);
        teams.add(team2);

        // Standaard tijd voor een ronde
        timeRound = 9;

    }

    public void startGame(){
        //? maak instucties voor players ?

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
    public void addTime(){
        // ook nog met timer tijd aftrek
        if (correctInstruction > 3){
            time++;
        }
    }

    public void subtractLives(){
        if (this.time == 3){
            lives--;
        }
        //new round start
    }

    public void subtractTime(Team otherTeam){
        //voorbeeld van time correctie op goede otherteam
        if (otherTeam.correctInstruction > 6){
            time--;
        }
    }
    public void addPlayer(Player p, Team t){
        t.getPlayers().add(p);
    }


    public void removePlayer(Player p){
        players.remove(p);
    }
    public void addCorrectInstruction(Panel donePanel){
        int currentCorrect = correctInstruction;
        for (Player p : players){

            if (p.instructions.getPanel() == donePanel) {
                correctInstruction++;
            }
            //player instruction controleren op doorgegeven Paneel
        }
        if (!(currentCorrect < correctInstruction)){
            correctInstruction = 0;
        }
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
    public void getAllPlayerPanels(){
        for (Player p : players){
            for (Panel pan : p.panels){
                playerPanels.add(pan);
            }
        }
    }
}
