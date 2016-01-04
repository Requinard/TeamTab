package networking.mediator;

import Game.*;
import Game.adapters.InstructionAdapter;
import Game.adapters.PanelAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;
import org.junit.Before;

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

    //todo: @Test
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
    //todo: @Test
    public void testHandleInstruction() throws Exception {
        Instruction instruction = new Instruction(null, 1, null);

        NetworkRequest networkRequest = new NetworkRequest(RequestType.SEND, "/instruction/", InstructionAdapter.toString(instruction));

        clientMediator.handleInstruction(networkRequest);
    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    //todo: @Test
    public void testHandleTeams() throws Exception {
        Team team1 = new Team("team1");
        Team team2 = new Team("team2");

        List<Team> teams = new ArrayList<>();

        NetworkRequest networkRequest = new NetworkRequest(RequestType.SEND, "/teams/", TeamAdapter.toString(teams));

        clientMediator.handleStatus(networkRequest);

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testHandlePanels() throws Exception {
        List<Panel> panels = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            panels.add(new Panel(i, 1, 5, "panel", PanelTypeEnum.HorizontalSlider));
        }

        NetworkRequest networkRequest = new NetworkRequest(RequestType.SEND, "/panels/", PanelAdapter.toString(panels));

        clientMediator.handlePanels(networkRequest);
    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    //todo: @Test
    public void testHandleStatus() throws Exception {

    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testCreatePlayer() throws Exception {
        Player player = new Player("Kaj", "0.0.0.0");

        clientMediator.createPlayer(player);
    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    //todo: @Test
    public void testCreateTeam() throws Exception {
        Team team = new Team("team");

        clientMediator.createTeam(team);
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testAssignTeam() throws Exception {
        Team team = new Team("team1");

        clientMediator.assignTeam(team);
    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    //todo: @Test
    public void testProcessPanel() throws Exception {
        Panel panel = new Panel(1, 0, 1, "panel", PanelTypeEnum.values()[1]);

        clientMediator.processPanel(panel);
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testRegisterInvalidInstruction() throws Exception {
        Panel panel = new Panel(1, 0, 5, "test", PanelTypeEnum.HorizontalSlider);

        Player player = new Player("Kaj", "0.0.0.0");

        Instruction instruction = new Instruction(panel, 3, player);

        clientMediator.registerInvalidInstruction(instruction);
    }

    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    //todo: @Test
    public void testSetPlayerStatus() throws Exception {
        Player player = new Player("Frank", "127.0.0.1");
        player.setPlayerStatus(true);

        clientMediator.setPlayerStatus(player);
    }
}