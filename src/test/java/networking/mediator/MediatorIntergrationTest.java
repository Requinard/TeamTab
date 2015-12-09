package networking.mediator;

import Game.ClientGame;
import Game.HostGame;
import Game.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.awt.windows.ThemeReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by frank on 30/11/2015.
 */
public class MediatorIntergrationTest {

    HostGame hostGame;
    ClientGame clientGame;

    @Before
    public void setUp() throws Exception {
        hostGame = new HostGame();
        clientGame = new ClientGame();
        clientGame.setHostIp("127.0.0.1");
    }

    @After
    public void tearDown() throws Exception {
        hostGame = null;
        clientGame = null;
        System.out.println("teared down");
    }

    @Test
    public void joinGameTest() throws InterruptedException {
        //hostGame.createTeam("appels");

        hostGame.createTeam("a");
        hostGame.createTeam("b");
        clientGame.createPlayer("frank1", "127.0.0.1");
        Thread.sleep(1000);
        while(true) {
            if ((hostGame.getPlayers().size() > 0))
                break;
        }

        assertTrue(hostGame.getPlayers().size() > 0);

        //assertTrue(clientGame.getPlayers().size() > 0);
        //assertTrue(clientGame.getTeams().size() > 0);

    }

    /*
    Test if the teams are added to the hostgame and if they are received in the clientgame
    also tests if the teamname is still set
     */
    @Test
    public void getTeamsTest() throws InterruptedException {
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
               System.out.println("Teams: "+clientGame.getTeams().size());
                break;
            }
        }
        assertTrue(clientGame.getTeams().size() > 1);
        assertTrue(hostGame.getTeams().size() > 1);
        assertEquals("Team1", clientGame.getTeams().get(0).getName());
        assertEquals("Team2",clientGame.getTeams().get(1).getName());
    }
    /*
    Test if the amount of lifes of a team can be modified.
     */
    @Test
    public void TeamLevensTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        Team team1 = hostGame.getTeams().get(1);
        team1.changeLives(-1);
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        Team clientTeam = clientGame.getTeams().get(1);
        assertTrue(clientTeam.getLives() == hostGame.getTeams().get(1).getLives());
        assertTrue(clientTeam.getLives() == 2);
    }
    /*
    Test if the time which a team has to complete an instruction can be modified
     */
    @Test
    public void TeamTimeTest() throws InterruptedException {
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        Team team1 = hostGame.getTeams().get(1);
        team1.changeTime(-2);
        System.out.println(team1.getTime());
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        Team clientTeam = clientGame.getTeams().get(1);
        assertTrue(clientTeam.getTime() == hostGame.getTeams().get(1).getTime());
        assertTrue(clientTeam.getTime() == 7);
    }
    /*
    Test if the score of the team on the host is the same as the team on the client
    */
    @Test
    public void TeamScoreTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertEquals(0, clientGame.getTeams().get(1).getScore());
    }
    /*
    Test if the team is alive when its killed on the hostGame
     */
    @Test
    public void TeamIsAliveTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        hostGame.getTeams().get(1).changeLives(-3);
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertEquals(clientGame.getTeams().get(1).isAlive(), false);
    }
}