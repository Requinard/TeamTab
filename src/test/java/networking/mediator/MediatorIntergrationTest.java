package networking.mediator;

import Game.ClientGame;
import Game.HostGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by frank on 30/11/2015.
 */
public class MediatorIntergrationTest {
    HostGame hostGame;
    ClientGame clientGame;

    @Before
    public void setUp() throws Exception {
        hostGame = new HostGame(8095);
        clientGame = new ClientGame(8095);
        clientGame.setHostIp("127.0.0.1");
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void joinGameTest() throws InterruptedException {
        //hostGame.createTeam("appels");


        clientGame.createPlayer("frank1", "127.0.0.1");

        Thread.sleep(2000);

        assertTrue(hostGame.getPlayers().size() > 0);

        Thread.sleep(2000);

        //assertTrue(clientGame.getPlayers().size() > 0);
        //assertTrue(clientGame.getTeams().size() > 0);

    }

    /*
    Test if the teams are added to the hostgame and if they are received in the clientgame
     */
    @Test
    public void getTeamsTest() throws InterruptedException {
        clientGame.createTeam("Team1");
        Thread.sleep(50);
        assertTrue(hostGame.getTeams().size() > 0);
        Thread.sleep(50);
        assertTrue(clientGame.getTeams().size() > 0);
    }
}