package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class GameTest {

    Game g = new Game();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStartGame() throws Exception {

    }

    @Test
    public void testNewRound() throws Exception {


        assertEquals("Geen leven minder", 2, g.team1.getLives());
        g.newRound();
        assertEquals("levens niet gereset", 3, g.team1.getLives());
    }

    @Test
    public void testEndGame() throws Exception {

    }

    @Test
    public void testAddPlayerToTeam() throws Exception {
        Player p1 = new Player();
        Player p2 = new Player();
        g.addPlayerToTeam(p1);

        assertEquals("add to wrong team", 1, g.team1.getPlayers().size());
        assertEquals("add to wrong team", 0, g.team2.getPlayers().size());
        g.addPlayerToTeam(p2);
        assertEquals("add to wrong team", 1, g.team2.getPlayers().size());
    }
}