package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Kaj Suiker on 16-11-2015.
 */
public class ClientGameTest {

    ClientGame game;

    @Before
    public void setUp() throws Exception {
        game = new ClientGame();
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
        Assert.assertEquals("Incorrect name for team", "test1", team.getName());

        //Tests if team is added to list of teams
        team = game.createTeam("test2");
        Assert.assertEquals("Team is not added to list teams", game.getTeams().get(1), team);
    }

    /**
     * Checks if it is possible to make a team with a empty string
     * Author Kaj Suiker
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCreateTeamEmptyString() {
        game.createTeam("");
    }

    @Test
    public void testCreatePlayer() throws Exception {

    }

    @Test
    public void testAssignTeam() throws Exception {

    }

    @Test
    public void testReset() throws Exception {

    }

    @Test
    public void testStartRound() throws Exception {

    }

    @Test
    public void testProcessPanel() throws Exception {

    }

    @Test
    public void testHasGameEnded() throws Exception {

    }

    @Test
    public void testRegisterInvalidInstruction() throws Exception {

    }
}