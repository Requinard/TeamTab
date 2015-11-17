package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Kaj Suiker on 16-11-2015.
 */

public class ClientGameTest {

    ClientGame game;
    List<Panel> panelsList;
    List<Team> teamsList;


    @Before
    public void setUp() throws Exception {
        game = new ClientGame();
        panelsList = new ArrayList<>();
        teamsList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPlayers() throws Exception {

    }

    @Test
    public void testGetTeams() throws Exception {

    }

    @Test
    public void testGetPanels() throws Exception {

    }

    /**
     * Author Kaj Suiker
     *
     * @throws Exception
     */
    @Test
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
    @Test(expected = UnsupportedOperationException.class)
    public void testCreateTeamEmptyString() {
        game.createTeam("");
    }

    /**
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
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
    @Test(expected = UnsupportedOperationException.class)
    public void testCreatePlayerNoName() {
        game.createPlayer("", "0.0.0.0");
    }

    /**
     * Author Kaj
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCreatePlayerNoIpAddress() {
        game.createPlayer("Kaj", null);
    }


    @Test
    public void testAssignTeam() throws Exception {

    }

    @Test
    public void testReset() throws Exception {

    }

    @Test
    public void testStartRound() throws Exception {
        // check if the panels al loaded from the file and added to the panels list in ClientGame
        panelsList = game.getPanels();
        assertNotNull(panelsList);
        // check if the method give all the team their panels
        teamsList = game.getTeams();
        for (Team team : teamsList) {
            assertNotNull(team.getPanels());
        }
    }

    @Test
    public void testProcessPanel() throws Exception {
        Player player = game.createPlayer("kaj", "0.0.0.0");
        game.startRound();
        //still no active instructions
        //boolean correctInstruction = game.processPanel(player, player.getPanels().get(0));
        //Assert.assertEquals("no active instruction", false, correctInstruction);
    }

    @Test
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

    @Test
    public void testRegisterInvalidInstruction() throws Exception {

    }
}