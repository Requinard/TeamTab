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
        hostGame = new HostGame();
        clientGame = new ClientGame();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void joinGameTest() throws InterruptedException {
        clientGame.setHostIp("127.0.0.1");
        hostGame.createTeam("appels");
        clientGame.createPlayer("frank1", "127.0.0.1");

        Thread.sleep(2000);

        assertTrue(hostGame.getPlayers().size() > 0);

        Thread.sleep(2000);

        assertTrue(clientGame.getPlayers().size() > 0);
        assertTrue(clientGame.getTeams().size() > 0);


    }

    @Test
    public void getTeamsTest() throws InterruptedException {

    }


}