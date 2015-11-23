package networking.mediator;

import Game.ClientGame;
import Game.Player;
import Game.adapters.PlayerAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/23/2015.
 */
public class ClientMediatorTest {
    ClientMediator clientMediator;
    ClientGame clientGame;

    @Before
    public void setUp() {
        clientGame = new ClientGame();
        clientMediator = new ClientMediator(clientGame);
    }

    @Test(expected = NotImplementedException.class)
    public void testHandlePlayers() throws Exception {
        List<Player> playerList = new ArrayList<>();

        Player p = new Player("kanker zooi", "localhost");

        playerList.add(p);

        NetworkRequest networkRequest = new NetworkRequest(RequestType.SEND, "/player/", PlayerAdapter.toString(playerList));

        clientMediator.handlePlayers(networkRequest);
    }
}