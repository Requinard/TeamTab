package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class TeamTest {

    private Team t;
    private Player p1;
    private Player p2;

    @Before
    public void setUp() throws Exception {
        t = new Team();
        p1 = new Player();
        p2 = new Player();
        t.addPlayer(p1);
        t.addPlayer(p2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPlayers() throws Exception {

    }

    @Test
    public void testAddTime() throws Exception {

    }

    @Test
    public void testSubtractLives() throws Exception {

    }

    @Test
    public void testSubtractTime() throws Exception {

    }

    @Test
    public void testAddPlayer() throws Exception {

    }

    @Test
    public void testRemovePlayer() throws Exception {

    }

    @Test
    public void testAddCorrectInstruction() throws Exception {

    }

    @Test
    public void testReset() throws Exception {

    }

    @Test
    public void testGetAllPlayerPanels() throws Exception {
        assertEquals("not empty", 0, t.getPlayerPanels().size());
        t.getAllPlayerPanels();
        assertEquals("not all panels have been added", 3, t.getPlayerPanels());
    }
}