package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by david on 12-10-15.
 */
public class GameTest {

    Game g;
    Player p1;
    Player p2;
    ArrayList<Panel> pp1;
    Panel pan1;
    Panel pan2;
    Instruction in1;
    Instruction in2;

    /**
     * Sets up the test class
     *
     * @throws Exception
     * @author David
     * @note Removed instruction because panels are no longer initialized with an instruction
     */
    @Before
    public void setUp() throws Exception {
        g  = new Game();

        pan1 = new Panel(1, 1, "a", 0, 1);
        pan2 = new Panel(2, 1, "b", 0, 1);
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        pp1 = new ArrayList<Panel>();
        pp1.add(pan1);
        pp1.add(pan2);
        p1 = new Player("1.1.1.1", "Kaj", 0, pp1, in1, g, null); //team later toevoegen
        p2 = new Player("2.2.2.2", "Frank", 0, new ArrayList<Panel>(), null, g, null);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStartGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.startGame();
    }

    @Test
    public void testStartGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.startGame();
        assertEquals("not all player panels added", 2, g.team1.getPlayerPanels().size());
        assertEquals("not all player panels added", 0, g.team2.getPlayerPanels().size());


    }

    @Test
    public void testNewRound() throws Exception {


    }

    @Test
    public void testEndGame() throws Exception {

    }

    @Test
    public void testAddPlayerToTeam() throws Exception {
        g.addPlayerToTeam(p1);
        assertEquals("added to wrong team", g.team1.getPlayers().get(0), p1);
        g.addPlayerToTeam(p2);
        assertEquals("added to wrong team", g.team2.getPlayers().get(0), p2);
    }

    @Test
    public  void testchangeTeam() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.changeTeam(p1);
        assertEquals("Wrong size team", 0, g.team1.getPlayers().size());
        assertEquals("Wrong size team", 2, g.team2.getPlayers().size());
        g.changeTeam(p2);
        assertEquals("added to the wrong team", p2, g.team1.getPlayers().get(0));
        assertEquals("added to the wrong team", p1, g.team2.getPlayers().get(0));
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

    //Vanaf hier doet Frank
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

    }
}