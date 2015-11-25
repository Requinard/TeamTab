package networking.mediator;

import Game.ClientGame;
import Game.Player;
import Game.adapters.PlayerAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testHandlePlayers() throws Exception {
        List<Player> playerList = new ArrayList<>();

        Player p = new Player("kanker zooi", "localhost");

        playerList.add(p);

        NetworkRequest networkRequest = new NetworkRequest(RequestType.SEND, "/player/", PlayerAdapter.toString(playerList));

        clientMediator.handlePlayers(networkRequest);
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testHandleInstruction() throws Exception {

    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testHandleTeams() throws Exception {

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testHandlePanels() throws Exception {

    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testHandleStatus() throws Exception {

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testCreatePlayer() throws Exception {

    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testCreateTeam() throws Exception {

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testAssignTeam() throws Exception {

    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testProcessPanel() throws Exception {

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testRegisterInvalidInstruction() throws Exception {

    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testSetPlayerStatus() throws Exception {

    }
}