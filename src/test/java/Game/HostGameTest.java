package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Kaj Suiker on 16-11-2015.
 */

public class HostGameTest {

    HostGame game;
    List<Panel> panelsList;
    List<Team> teamsList;


    @Before
    public void setUp() throws Exception {
        game = new HostGame();
        panelsList = new ArrayList<>();
        teamsList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    //todo: @Test
    public void testGetPlayers() throws Exception {
        game.createPlayer("Vito", "localhost");
        game.createPlayer("Corleone", "localhost");
        assertEquals(2, game.getPlayers().size());
    }

    //todo: @Test
    public void testGetTeams() throws Exception {
        game.createTeam("Vito's");
        assertEquals(1, game.getTeams().size());
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testGetPanels() throws Exception {
        //check if the list of panels is not empty
        Assert.assertTrue("Can't get list of panels from the game", !game.getPanels().isEmpty());
    }

    /**
     * Author Kaj Suiker
     *
     * @throws Exception
     */
    //todo: @Test
    public void testCreateTeam() throws Exception {
        //Tests if the correct name is given to the team
        Team team;
        team = game.createTeam("test1");
        assertEquals("Incorrect name for team", "test1", team.getName());

        //Tests if team is added to list of teams
        team = game.createTeam("test2");
        assertEquals("Team is not added to list teams", game.getTeams().get(1), team);
    }

    /**
     * Checks if it is possible to make a team with a empty string
     * Author Kaj Suiker
     */
    //todo: @Test(expected = UnsupportedOperationException.class)
    public void testCreateTeamEmptyString() {
        game.createTeam("");
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testCreatePlayer() throws Exception {
        String username = "Kaj";
        String ipAddress = "0.0.0.0";

        Player player = game.createPlayer(username, ipAddress);
        //checks if right parameters are given to Player
        Assert.assertEquals("Wrong name given", username, player.getUsername());
        Assert.assertEquals("Wrong ip address", ipAddress, player.getIp());
    }

    /**
     * Author Kaj
     */
    //todo: @Test(expected = UnsupportedOperationException.class)
    public void testCreatePlayerNoName() {
        game.createPlayer("", "0.0.0.0");
    }

    /**
     * Author Kaj
     */
    //todo: @Test(expected = UnsupportedOperationException.class)
    public void testCreatePlayerNoIpAddress() {
        game.createPlayer("Kaj", null);
    }

    /**
     * Author Kaj
     * @throws Exception
     */
    //todo: @Test
    public void testAssignTeam() throws Exception {
        Player player = game.createPlayer("Kaj", "0.0.0.0");
        Team team = game.createTeam("Team1");
        //check if the list of players is empty
        Assert.assertTrue("List of players in team is not empty", team.getPlayers().isEmpty());

        //check if player has been added
        game.assignTeam(player, team);
        Assert.assertTrue("Player not added to list of players in team", team.getPlayers().contains(player));

        //check if double assigning to a team is possible
        game.assignTeam(player, team);
        Assert.assertEquals("team has double players", 1, team.getPlayers().size());
    }

    //todo: @Test
    public void testReset() throws Exception {
        Team team1 = game.createTeam("team1");
        Team team2 = game.createTeam("team2");
        int timeTeam1 = team1.getTime();
        int timeTeam2 = team2.getTime();
        int livesTeam1 = team1.getLives();
        int livesTeam2 = team2.getLives();


        // The soft reset should only reset the time of all the teams in the game
        team1.changeTime(-3);
        team2.changeTime(-2);

        Assert.assertEquals(timeTeam1 - 3, team1.getTime());
        Assert.assertEquals(timeTeam2 - 2, team2.getTime());

        game.reset(false);

        // Checks if the time has been reset to the beginning
        Assert.assertEquals(timeTeam1, team1.getTime());
        Assert.assertEquals(timeTeam2, team2.getTime());

        // The hard reset should reset the time and lives of all the teams in the game
        team1.changeTime(-5);
        team2.changeTime(-4);
        Assert.assertEquals(timeTeam1 - 5, team1.getTime());
        Assert.assertEquals(timeTeam2 - 4, team2.getTime());

        team1.changeLives(-2);
        team2.changeLives(-1);
        Assert.assertEquals(livesTeam1 - 2, team1.getLives());
        Assert.assertEquals(livesTeam2 - 1, team2.getLives());

        game.reset(true);

        Assert.assertEquals(timeTeam1, team1.getTime());
        Assert.assertEquals(timeTeam2, team2.getTime());
        Assert.assertEquals(livesTeam1, team1.getLives());
        Assert.assertEquals(livesTeam2, team2.getLives());
    }

    //todo: @Test
    public void testStartRound() throws Exception {
        // check if the panels al loaded from the file and added to the panels list in HostGame
        panelsList = game.getPanels();
        assertNotNull(panelsList);
        // check if the method give all the team their panels
        teamsList = game.getTeams();
        for (Team team : teamsList) {
            assertNotNull(team.getPanels());
        }
    }

    /**
     * author Kaj
     *
     * @throws Exception
     */
    //todo: @Test
    public void testProcessPanel() throws Exception {
        Player player1 = game.createPlayer("Kaj1", "0.0.0.0");
        Player player2 = game.createPlayer("Kaj2", "0.0.0.0");
        Team team1 = game.createTeam("Team1");
        Team team2 = game.createTeam("Team2");
        game.assignTeam(player1, team1);
        game.assignTeam(player2, team2);
        game.startRound();

        //check if the active instuction panel for player 1 is a correct panel
        Assert.assertTrue("players instruction is not a active instuction", game.processPanel(player1, player1.getActiveInstruction().getPanel()));
    }

    //todo: @Test
    public void testHasGameEnded() throws Exception {
        Team team1 = game.createTeam("Team1");
        Team team2 = game.createTeam("Team2");
        Team team3 = game.createTeam("Team3");

        Assert.assertFalse(game.hasGameEnded());
        team1.changeLives(-3);
        Assert.assertFalse(game.hasGameEnded());
        team2.changeLives(-5);
        Assert.assertTrue(game.hasGameEnded());

    }

    //todo: @Test
    public void testRegisterInvalidInstruction() throws Exception {
        // create list of panels
        List<Panel> listPanels = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Panel panel = new Panel(i, 1, 5, "Slider1", PanelTypeEnum.HorizontalSlider);
            // add panel to list
            listPanels.add(panel);
        }
        // create player
        Player player1 = game.createPlayer("Kamil", "0.0.0.0");
        // create team
        Team team1 = new Team("TestTeam");
        // add player to team
        team1.addPlayer(player1);
        // generate panels for player
        team1.generatePanels(listPanels);
        // create instruciton
        Instruction instruction = new Instruction(listPanels.get(1), 1, player1);
        game.registerInvalidInstruction(instruction);
        Assert.assertNotSame("The player did not get a new instruction", instruction, player1.getActiveInstruction());
    }


    //todo: @Test
    public void testGetScoreboard() throws Exception {
        Team team1 = game.createTeam("team1");
        Team team2 = game.createTeam("team2");

        Player player1 = game.createPlayer("player1", "127.0.0.1");
        Player player2 = game.createPlayer("player2", "127.0.0.1");

        game.assignTeam(player1, team1);
        game.assignTeam(player2, team2);

        game.startRound();

        Instruction invalidInstruction = player1.getActiveInstruction();

        game.registerInvalidInstruction(invalidInstruction);
        game.processPanel(player2, player2.getActiveInstruction().getPanel());
        game.processPanel(player2, player2.getActiveInstruction().getPanel());

        List<String> scores = game.getScoreboard();
        Assert.assertEquals("team1 - player1 : -1", scores.get(0));
        Assert.assertEquals("team2 - player2 : 2", scores.get(1));


    }
}