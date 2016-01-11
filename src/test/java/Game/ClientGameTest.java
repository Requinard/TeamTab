package Game;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by frank on 23/11/2015.
 */
public class ClientGameTest {
    private ClientGame clientGame;
    private HostGame hostGame;

    @Before
    public void setUp() throws Exception {
        clientGame = new ClientGame();
        hostGame = new HostGame();
    }

    @Test
    public void testGetHostIP() throws Exception {
        //Het Ip adress van de host moet eerst nog gevonden kunnen worden.
        clientGame.getHostIP();
    }

    @Test
    public void testGetPlayers() throws Exception {
        List<Player> players = new ArrayList<>();

        players.add(new Player("player1", "127.0.0.1"));
        players.add(new Player("player2", "127.0.0.1"));

        clientGame.setPlayers(players);

        Assert.assertEquals(players, clientGame.getPlayers());
    }

    @Test
    public void testSetPlayers() throws Exception {
        // testGetPlayers is testing the get and set function at the same time
        List<Player> players = new LinkedList<Player>();

        players.add(new Player("test", "1"));

        clientGame.setPlayers(players);
    }

    @Test
    public void testGetTeams() throws Exception {
        List<Team> teams = new ArrayList<>();

        teams.add(new Team("team1"));
        teams.add(new Team("team2"));

        clientGame.setTeams(teams);

        Assert.assertEquals(teams, clientGame.getTeams());
    }

    @Test
    public void testSetTeams() throws Exception {
        // testGetTeam is testing the get and set function at the same time
    }

    @Test
    public void testGetPanels() throws Exception {
        List<Panel> panels = new ArrayList<>();

        panels.add(new Panel(0, 1, 2, "test", PanelTypeEnum.HorizontalSlider));
        panels.add(new Panel(1, 1, 2, "test", PanelTypeEnum.HorizontalSlider));

        clientGame.setPanels(panels);

        Assert.assertEquals(panels, clientGame.getPanels());
    }

    @Test
    public void testCreateTeam() throws Exception {
        // Frank Hartman: Hoe kan deze methode getest worden?
        clientGame.createTeam("test"); // zo dus
    }

    @Test
    public void testCreatePlayer() throws Exception {
        clientGame.createTeam("test team");
        clientGame.createPlayer("test", "test");
    }

    @Test(expected = AssertionFailedError.class)
    public void testAssignTeam() throws Exception {
        String teamName = "team1";
        Team team = clientGame.createTeam(teamName);

        Assert.assertTrue(clientGame.getTeams().contains(team));
    }

    @Test
    public void testReset() throws Exception {
        clientGame.reset(true);
    }

    @Test
    public void testStartRound() throws Exception {
        clientGame.startRound();
    }

    @Test
    public void testProcessPanel() throws Exception {
        clientGame.processPanel(new Player("test", "test"), new Panel(1, 1, 2, "test", PanelTypeEnum.Button));
    }

    @Test
    public void testHasGameEnded() throws Exception {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Team1"));
        teams.add(new Team("Team2"));
        teams.add(new Team("Team3"));

        clientGame.setTeams(teams);

        Assert.assertFalse(clientGame.hasGameEnded());
        teams.get(0).changeLives(-3);
        Assert.assertFalse(clientGame.hasGameEnded());
        teams.get(1).changeLives(-5);
        Assert.assertTrue(clientGame.hasGameEnded());
    }

    @Test
    public void testRegisterInvalidInstruction() throws Exception {
        Player p = new Player("p", "1");
        clientGame.setLocalPlayer(p);
        clientGame.registerInvalidInstruction(new Instruction(new Panel(1, 1, 2, "test", PanelTypeEnum.Button), 1, p));
    }

    @Test
    public void testChangePlayerStatus() throws Exception {
        clientGame.setLocalPlayer(new Player("test", "test"));
        clientGame.changePlayerStatus(true);
    }

    @Test
    public void testSetGameState() throws Exception {
        clientGame.setGameState(GameStateEnum.GameView);
    }

    @Test
    public void testScheduleRefresh() throws Exception {
        clientGame.scheduleRefresh();
    }

    @Test
    public void testGetLocalIP() throws Exception {
        testSetLocalIP();
        clientGame.getLocalIP();
    }

    @Test
    public void testSetLocalIP() throws Exception {
        clientGame.setLocalIP("127.0.0.1");
    }

    @Test
    public void testSetHostIp() throws Exception {
        clientGame.setHostIp("127.0.0.2");
    }

    @Test
    public void testSetPanels() throws Exception {
        clientGame.setPanels(new LinkedList<>());

        assertTrue(clientGame.getPanels().size() == 0);
    }

    @Test
    public void testUpdate() throws Exception {
        clientGame.setGameState(GameStateEnum.LobbyView);
        clientGame.update();
        clientGame.setGameState(GameStateEnum.GameView);
        clientGame.update();
        clientGame.setGameState(GameStateEnum.ScoreView);
        clientGame.update();
    }

    @Test
    public void testSetLocalPlayer() throws Exception {
        clientGame.setLocalPlayer(new Player("p", "1"));
    }

    @Test
    public void testGetTeam() throws Exception {
        List<Team> teams = new LinkedList<>();
        teams.add(new Team("test"));
        clientGame.setTeams(teams);
        Team t = clientGame.getTeam("test");

        assertNotNull(t);
    }

    @Test
    public void testGetPlayer() throws Exception {
        List<Player> players = new LinkedList<>();
        players.add(new Player("p", "1"));
        clientGame.setPlayers(players);
        Player p = clientGame.getPlayer("p");

        assertNotNull(p);
    }

    @Test
    public void testStopSchedule() throws Exception {
        clientGame.scheduleRefresh();
        clientGame.stopSchedule();
    }

}