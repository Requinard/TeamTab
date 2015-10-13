package Game;

import java.util.ArrayList;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;

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

    }

    public void startGame(){
        //? maak instucties voor players ?
    }

    public void newRound(){
        for (Team t : teams){
            t.reset();
        }
        //nieuw panels voor players
    }

    public void endGame(){
        for (Player p : team1.getPlayers()){
            //p.score;
            //add score to scoreview
        }
        for (Player p : team2.getPlayers()){
            //p.score;
            //add score to scoreview
        }
        //show scoreboard
    }

    public void addPlayerToTeam(Player p){
        if(teams.get(0).getPlayers().size() <= teams.get(1).getPlayers().size())
        {
            teams.get(0).addPlayer(p);
        }else {
            teams.get(1).addPlayer(p);
        }
    }

    public  void changeTeam(Player p){

    }
}
