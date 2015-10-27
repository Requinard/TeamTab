package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 12-10-15.
 * @author Frank Hartman
 */
public class PlayerTest {
    Player player;
    Game game;
    Instruction instruction1;
    Instruction instruction2;
    Panel pan1;
    Panel pan2;
    ArrayList panels;
    Team team;


    /**
     * @author Frank Hartman
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        game = new Game(null);
        team = new Team(9, 3);
        instruction1 = new Instruction(pan1, "Click on", 0);
        instruction2 = new Instruction(pan2, "Click off", 1);
        pan1 = new Panel(1, 1, "a", 0, 1);
        pan2 = new Panel(2, 1, "b", 0, 1);
        instruction1.setPanel(pan1);
        instruction2.setPanel(pan2);
        panels = new ArrayList<Panel>();
        panels.add(pan1);
        panels.add(pan2);
        player = new Player("1.1.1.1", "FrankHartman", 0, panels, instruction1, game, null);
        team.addPlayerToTeam(player);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPanel() throws Exception {
        //waarschijnlijk niet nodig
        /*
        Panel testPanel;
        ArrayList<Panel> Panels = new ArrayList<Panel>();
        for (int i = 0; i < 3 ; i++) {
            Panels.add(new Panel());
        }
        Random r = new Random();
        int Random = r.nextInt(panels.size());
        testPanel =  Panels.get(Random);
        for(Panel p : Panels)
        {
            if(p == testPanel)
            {
                Assert.assertEquals(Random, Panels.indexOf(p));
            }
        }
        */

    }

    @Test
    public void testGetIpAdress() throws Exception {
        assertEquals("1.1.1.1", player.getIpAdress());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("FrankHartman", player.getName());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(0, player.getScore());
    }

    @Test
    public void testGetGame() throws Exception {
        assertEquals(game, player.getGame());
    }

    @Test
    public void testGetInstructions() throws Exception {
        assertEquals(instruction1, player.getInstructions());
    }

    @Test
    public void testSetInstructions() throws Exception {
        player.setInstructions(instruction2);
        assertEquals(instruction2, player.getInstructions());
    }

    @Test
    public void testGetTeam() throws Exception {
        assertEquals(team, player.getTeam());
    }

    @Test
    public void testSetTeam() throws Exception {
        player.setTeam(null);
        assertEquals(null, player.getTeam());
        player.setTeam(team);
        assertEquals(team, player.getTeam());
    }

    @Test
    public void testGetPanels() throws Exception {
        assertEquals(panels, player.getPanels());
    }

    @Test
    public void testSetPanels() throws Exception {
        ArrayList testPanels = new ArrayList<Panel>();
        player.setPanels(testPanels);
        assertEquals(testPanels, player.getPanels());
    }

    @Test
    public void testCheckCorrectPanel() throws Exception {
        assertTrue( player.checkCorrectPanel(pan1));
    }

    @Test
    public void testCompareTo() throws Exception {
        Player testPlayer = new Player("1.1.1.1", "Pieterke", 2, panels, instruction1, game, null);
        assertEquals(2, player.compareTo(testPlayer));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Player: FrankHartman", player.toString());
    }

    @Test
    public void testAddScore() {
        int testScore = player.getScore();
        player.addScore();
        assertEquals(testScore + 1, player.getScore());
    }
}