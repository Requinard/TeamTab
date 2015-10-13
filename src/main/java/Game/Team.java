package Game;

import java.util.ArrayList;

/**
 * Created by HP user on 12-10-2015.
 */
public class Team {
    private ArrayList<Player> players;
    private ArrayList<Panel> playerPanels;

    private int time = 9;
    private int lives;
    private int correctInstruction;

    //get

    public ArrayList<Player> getPlayers() {return players;}




    public Team(){
        players = new ArrayList<Player>();
    }

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
    public void addPlayer(Player p){
        players.add(p);
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
    public void reset(){
        lives = 3;
        time = 9;
        correctInstruction = 0;
    }
    public void getPlayerPanels(){
        for (Player p : players){
            for (Panel pan : p.panels){
                playerPanels.add(pan);
            }
        }
    }


}
