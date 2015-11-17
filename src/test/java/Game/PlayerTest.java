package game;

import Game.Player;
import Game.Team;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David on 11/16/2015.
 */
public class PlayerTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("Frank Hartman", "127.0.0.1");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetUsername() throws Exception {
        Assert.assertEquals("Frank Hartman", player.getUsername());
    }

    @Test
    public void testSetUsername() throws Exception {
        String username = "Barry Badpak";
        player.setUsername(username);
        Assert.assertEquals(username, player.getUsername());
    }

    @Test
    public void testGetIp() throws Exception {
        Assert.assertEquals("127.0.0.1", player.getIp());
    }

    @Test
    public void testGetPanels() throws Exception {
        Assert.assertNotNull(player.getPanels());
    }

    @Test
    public void testGetSetTeam() throws Exception {

        Assert.assertNull(player.getActiveInstruction());

        Team team = new Team("Team1");
        player.setTeam(team);
        Assert.assertEquals(team, player.getTeam());
    }


    @Test
    public void testGetActiveInstruction() throws Exception {


    }

    @Test
    public void testGeneratePanels() throws Exception {

    }

    @Test
    public void testHasPanel() throws Exception {

    }

    @Test
    public void testGenerateInstruction() throws Exception {

    }
}