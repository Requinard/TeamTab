package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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

    Panel pan3;
    Instruction in3;
    @Before
    public void setUp() throws Exception {
        g  = new Game();
        pan1 = new Panel(1, 1, "a", 0, 1, in1);
        pan2 = new Panel(2, 1, "b", 0, 1, in2);
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        pp1 = new ArrayList<Panel>();
        pp1.add(pan1);
        pp1.add(pan2);
        p1 = new Player("1.1.1.1", "Kaj", 0, pp1, in1, g, null); //team later toevoegen
        p2 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, g, null);
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
        g.newRound();
        //mogelijk nog uitgebreid
    }

    @Test
    public void testEndGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        assertEquals("Gives the wrong score", "Kaj: 0", g.endGame(g.team1).get(0));
        assertEquals("Gives the wrong score", "Frank: 3", g.endGame(g.team2).get(0));

        pan3 = new Panel(2, 1, "c", 0, 1, in3);
        in3 = new Instruction(pan3, "Click on", 0);
        ArrayList<Panel> pal = new ArrayList<Panel>();
        pal.add(pan3);
        Player p3 = new Player("1.2.3.4","Bas",5,pal, null, g,null);
        g.addPlayerToTeam(p3);

        assertEquals("Wrong order", "Bas: 5", g.endGame(g.team1).get(0));
        assertEquals("Not everyone added", 2, g.endGame(g.team1).size());
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